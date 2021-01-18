package com.alohagoha.volatilerecyclerview.model.repositories

import com.alohagoha.volatilerecyclerview.model.entities.NumberItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.util.*
import java.util.concurrent.atomic.AtomicInteger
import kotlin.random.Random

class NumberRepo(size: Int = 15) : INumberRepo {

    private val synchronizeObj = Mutex()
    private val nextNumber = AtomicInteger()
    override val stateNumbers: MutableStateFlow<List<NumberItem>> = MutableStateFlow(List(size) { createNextNumberItem() })
    private val backupPool: SortedSet<NumberItem> = sortedSetOf()

    private fun updateList(newList: List<NumberItem>) {
        stateNumbers.value = newList
    }

    private fun createNextNumberItem(): NumberItem {
        return NumberItem(nextNumber.getAndIncrement())
    }

    override suspend fun removeItem(position: Int) = withContext(Dispatchers.Default) {
        synchronizeObj.withLock {
            val newList = stateNumbers.value.toMutableList()
            backupPool.add(newList.removeAt(position))
            updateList(newList)
        }
    }

    override suspend fun addItemToRandomPosition() = withContext(Dispatchers.Default) {
        delay(5000L)
        synchronizeObj.withLock {
            val item = backupPool.takeUnless { it.isEmpty() }?.first()?.let { it.also { backupPool.remove(it) } }
                    ?: createNextNumberItem()
            val newList = stateNumbers.value.toMutableList()
            newList.add(Random.Default.nextInt(stateNumbers.value.size + 1), item)
            updateList(newList)
        }
    }

}
