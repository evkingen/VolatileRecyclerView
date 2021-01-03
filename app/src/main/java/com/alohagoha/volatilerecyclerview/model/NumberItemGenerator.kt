package com.alohagoha.volatilerecyclerview.model

import com.alohagoha.volatilerecyclerview.model.entities.NumberItem
import com.alohagoha.volatilerecyclerview.model.entities.OperationState
import com.alohagoha.volatilerecyclerview.model.entities.OperationType
import kotlin.random.Random

object NumberItemGenerator {
    var acc: Int = 0
    fun getNumberedItem() = NumberItem(acc++)

    fun getStartNumberedList(size: Int): List<NumberItem> =
        List(size) { getNumberedItem() }

    fun getStartState(size: Int): OperationState = OperationState(getStartNumberedList(15))

    fun addRandomPosition(state: OperationState): OperationState {
        Random.Default.nextInt(state.listItem.size + 1).let { position ->
            val newList =
                state.listItem.toMutableList().also { it.add(position, getNumberedItem()) }
            return OperationState(newList, position, OperationType.ADD)
        }
    }

    fun removeAt(state: OperationState, position: Int): OperationState {
        val newList = state.listItem.toMutableList().also { it.removeAt(position) }
        return OperationState(newList, position, OperationType.REMOVE)
    }
}
