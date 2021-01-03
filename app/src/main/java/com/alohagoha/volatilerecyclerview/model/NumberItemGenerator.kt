package com.alohagoha.volatilerecyclerview.model

import com.alohagoha.volatilerecyclerview.model.entities.NumberItem
import kotlin.random.Random

object NumberItemGenerator {
    var acc: Int = 0
    fun getNumberedItem() = NumberItem(acc++)

    fun getStartNumberedList(size: Int): List<NumberItem> =
        List(size) { getNumberedItem() }

//    fun getStartState(size: Int): OperationState = OperationState(getStartNumberedList(15))

    fun addRandomPosition(oldList: List<NumberItem>): List<NumberItem> {
        Random.Default.nextInt(oldList.size + 1).let { position ->
            return oldList.toMutableList().also { it.add(position, getNumberedItem()) }
        }
    }

    fun removeAt(oldList: List<NumberItem>, position: Int): List<NumberItem> {
        return oldList.toMutableList().also { it.removeAt(position) }
    }
}
