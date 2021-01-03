package com.alohagoha.volatilerecyclerview.model.repositories

import com.alohagoha.volatilerecyclerview.model.entities.NumberItem
import kotlin.random.Random

class NumberRepo {
    private var acc = 0
    private val _curList: MutableList<NumberItem> = mutableListOf()
    val curList: List<NumberItem>
        get() = _curList
    private var restoreList: MutableList<NumberItem> = mutableListOf()

    private fun getNumberedItem() =
        if (restoreList.isEmpty()) NumberItem(acc++) else restoreList.removeFirst()

    fun initStartList(size: Int) {
        _curList.clear()
        _curList.addAll(List(size) { getNumberedItem() })
    }

    fun addRandomPosition() {
        Random.Default.nextInt(curList.size + 1).let { index ->
            _curList.add(index, getNumberedItem())
        }
    }

    fun removeAt(position: Int) {
        restoreList.add(
            _curList.removeAt(position)
        )
    }
}
