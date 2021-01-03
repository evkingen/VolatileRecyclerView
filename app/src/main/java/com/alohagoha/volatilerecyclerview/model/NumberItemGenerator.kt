package com.alohagoha.volatilerecyclerview.model

import com.alohagoha.volatilerecyclerview.model.entities.NumberItem
import kotlin.random.Random

object NumberItemGenerator {
    private val curList: MutableList<NumberItem> = MutableList(15) { getNumberedItem() }
    private var acc: Int = 0
    fun getNumberedItem() = NumberItem(acc++)

    fun getStartNumberedList(): List<NumberItem> = curList

//    fun getStartState(size: Int): OperationState = OperationState(getStartNumberedList(15))

    fun addRandomPosition(): List<NumberItem> {
        Random.Default.nextInt(curList.size + 1).let { position ->
            curList.add(position, getNumberedItem())
            return curList
        }
    }


    fun removeAt(position: Int): List<NumberItem> {
        curList.removeAt(position)
        return curList
    }
/*
    fun getList() : Flow<List<NumberItem>> {
        return flow<List<NumberItem>> {
                delay(5000L)
                emit(getStartNumberedList(15))
        }
    }
*/
}

