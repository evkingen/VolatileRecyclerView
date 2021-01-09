package com.alohagoha.volatilerecyclerview.model.repositories

import com.alohagoha.volatilerecyclerview.model.entities.NumberItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.random.Random

class NumberRepo(
    override val activeList: MutableList<NumberItem>,
    override val backupList: MutableList<NumberItem>
) : INumberRepo {
    private var acc = 0

    private fun getNumberedItem() =
        if (backupList.isEmpty()) NumberItem(acc++) else backupList.removeFirst()

    override fun initStartList(size: Int): List<NumberItem> {
        activeList.clear()
        activeList.addAll(List(size) { getNumberedItem() })
        return activeList
    }

    override fun getNumbersList(): Flow<List<NumberItem>> = flow {
        while (true) {
            emit(activeList)
            addToRandomPositionItem()
        }
    }.flowOn(Dispatchers.Default)


    override fun removeItem(position: Int): List<NumberItem> {
        synchronized(activeList) {
            activeList.removeAt(position).also {
                synchronized(backupList) {
                    backupList.add(it)
                }
            }
        }
        return activeList
    }

    private suspend fun addToRandomPositionItem() {
        delay(5000L)
        synchronized(activeList) {
            Random.Default.nextInt(activeList.size + 1).let { index ->
                synchronized(backupList) {
                    val item = backupList.removeFirstOrNull() ?: getNumberedItem()
                    activeList.add(index, item)
                }
            }
        }
    }

}
