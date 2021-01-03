package com.alohagoha.volatilerecyclerview.ui.rv.adapters

import androidx.recyclerview.widget.DiffUtil
import com.alohagoha.volatilerecyclerview.model.entities.NumberItem

class DiffUtilsCallback(val oldList: List<NumberItem>, val newList: List<NumberItem>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].number == newList[newItemPosition].number
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
