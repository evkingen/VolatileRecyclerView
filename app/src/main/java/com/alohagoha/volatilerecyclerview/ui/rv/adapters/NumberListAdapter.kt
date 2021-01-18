package com.alohagoha.volatilerecyclerview.ui.rv.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.alohagoha.volatilerecyclerview.databinding.ViewHolderVolatileBinding
import com.alohagoha.volatilerecyclerview.model.entities.NumberItem

class NumberListAdapter(
        var numberList: List<NumberItem>,
        private val clickListener: AdapterOnClickListener
) : RecyclerView.Adapter<NumberListAdapter.NumberViewHolder>() {

    fun updateList(newList: List<NumberItem>) {
        val result = DiffUtil.calculateDiff(DiffUtilsCallback(numberList, newList))
        numberList = newList.toList()
        result.dispatchUpdatesTo(this)
    }

    private fun getNumberItem(position: Int) = numberList[position]

    override fun getItemCount(): Int = numberList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder {
        val itemBinding =
                ViewHolderVolatileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NumberViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class NumberViewHolder(val item: ViewHolderVolatileBinding) :
            RecyclerView.ViewHolder(item.root) {
        init {
            item.deleteIv.setOnClickListener {
                adapterPosition.takeUnless { it == RecyclerView.NO_POSITION }?.let(clickListener::onClick)
            }
        }


        fun bind(position: Int) {
            item.numberTv.text = getNumberItem(position).number.toString()
        }
    }

    fun interface AdapterOnClickListener {
        fun onClick(position: Int)
    }
}
