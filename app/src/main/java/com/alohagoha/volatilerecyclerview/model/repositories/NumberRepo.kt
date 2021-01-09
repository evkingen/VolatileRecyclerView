package com.alohagoha.volatilerecyclerview.model.repositories

import com.alohagoha.volatilerecyclerview.model.entities.NumberItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.*
import kotlin.random.Random

class NumberRepo(
    override val activeList: MutableList<NumberItem>,
    override val backupPool: SortedSet<NumberItem>
) : INumberRepo {
    private var acc = 0

    private fun getNumberedItem() = NumberItem(acc++)

    override fun initStartList(size: Int): List<NumberItem> {
        synchronized(activeList) {
            activeList.clear()
            activeList.addAll(List(size) { getNumberedItem() })
            return activeList
        }
    }

    override fun getNumbersList(): Flow<List<NumberItem>> = flow {
        while (true) {
            delay(5000L)
            addToRandomPositionItem()
            emit(activeList)
        }
    }.flowOn(Dispatchers.Default)


    override fun removeItem(position: Int): List<NumberItem> {
        synchronized(activeList) {
            activeList.removeAt(position).also {
                synchronized(backupPool) {
                    backupPool.add(it)
                }
            }
        }
        return activeList
    }

    private fun addToRandomPositionItem() {
        synchronized(activeList) {
            Random.Default.nextInt(activeList.size + 1).let { index ->
                synchronized(backupPool) {
                    val item = if (backupPool.isEmpty()) getNumberedItem() else backupPool.first()
                        .also { backupPool.remove(it) }
                    activeList.add(index, item)
                }
            }
        }
    }

}
