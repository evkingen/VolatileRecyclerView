package com.alohagoha.volatilerecyclerview

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.alohagoha.volatilerecyclerview.databinding.FragmentListBinding
import com.alohagoha.volatilerecyclerview.model.entities.OperationState
import com.alohagoha.volatilerecyclerview.model.entities.OperationType
import com.alohagoha.volatilerecyclerview.ui.NumberListViewModel
import com.alohagoha.volatilerecyclerview.ui.rv.adapters.VolatileListAdapter

class ListFragment : Fragment(R.layout.fragment_list) {

    private val viewModel: NumberListViewModel by viewModels()
    private var _listViewBinding: FragmentListBinding? = null
    private val listViewBinding
        get() = _listViewBinding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _listViewBinding = FragmentListBinding.bind(view)
        viewModel.numberList.observe(viewLifecycleOwner, ::reduce)
    }

    override fun onStart() {
        super.onStart()
        initRv()

        viewModel.insertSomeData()
    }

    private fun reduce(state: OperationState) {
        (listViewBinding.volatileRv.adapter as? VolatileListAdapter)?.apply {
            if (numberList.isNotEmpty() && numberList != state.listItem) {

                numberList = state.listItem
                when (state.operation) {
                    OperationType.REMOVE -> {
                        notifyItemRemoved(state.position)
                    }
                    OperationType.ADD -> {
                        //notifyDataSetChanged()
                        notifyItemInserted(state.position)
                    }
                    OperationType.NOTHING -> {
                        notifyDataSetChanged()
                    }
                }
            } else {


                numberList = state.listItem
                notifyDataSetChanged()
            }
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
