package com.alohagoha.volatilerecyclerview.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.alohagoha.volatilerecyclerview.R
import com.alohagoha.volatilerecyclerview.databinding.FragmentListBinding
import com.alohagoha.volatilerecyclerview.model.entities.NumberItem
import com.alohagoha.volatilerecyclerview.ui.rv.adapters.DiffUtilsCallback
import com.alohagoha.volatilerecyclerview.ui.rv.adapters.VolatileListAdapter

class ListFragment : Fragment(R.layout.fragment_list) {

    private val viewModel: NumberListViewModel by viewModels()
    private var _listViewBinding: FragmentListBinding? = null
    private val listViewBinding
        get() = _listViewBinding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _listViewBinding = FragmentListBinding.bind(view)
        viewModel.numberList.observe(viewLifecycleOwner, ::updateList)
    }

    override fun onStart() {
        super.onStart()
        initRv()
    }

    private fun updateList(newList: List<NumberItem>) {
        (listViewBinding.volatileRv.adapter as? VolatileListAdapter)?.apply {
            val result = DiffUtil.calculateDiff(DiffUtilsCallback(numberList, newList))
            numberList = newList.toList()
            result.dispatchUpdatesTo(this)
        }
    }

    private fun initRv() {
        listViewBinding.volatileRv.apply {
            itemAnimator = DefaultItemAnimator()
            layoutManager = GridLayoutManager(
                context,
                resources.getInteger(R.integer.volatile_list_span_count),
                GridLayoutManager.VERTICAL,
                false
            )
            adapter = VolatileListAdapter(listOf()) {
                viewModel.removeItem(it)
            }
            setHasFixedSize(true)
        }
    }
}
