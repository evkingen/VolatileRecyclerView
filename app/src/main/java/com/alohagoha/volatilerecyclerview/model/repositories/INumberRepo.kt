package com.alohagoha.volatilerecyclerview.model.repositories

import com.alohagoha.volatilerecyclerview.model.entities.NumberItem
import kotlinx.coroutines.flow.Flow
import java.util.*

interface INumberRepo {
    val activeList: MutableList<NumberItem>
    val backupPool: SortedSet<NumberItem>

    fun initStartList(size: Int): List<NumberItem>
    fun getNumbersList(): Flow<List<NumberItem>>
    fun removeItem(position: Int): List<NumberItem>
}
