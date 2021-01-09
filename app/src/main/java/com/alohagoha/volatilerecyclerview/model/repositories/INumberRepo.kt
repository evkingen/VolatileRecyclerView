package com.alohagoha.volatilerecyclerview.model.repositories

import com.alohagoha.volatilerecyclerview.model.entities.NumberItem
import kotlinx.coroutines.flow.Flow

interface INumberRepo {
    val activeList: MutableList<NumberItem>
    val backupList: MutableList<NumberItem>

    fun initStartList(size: Int): List<NumberItem>
    fun getNumbersList(): Flow<List<NumberItem>>
    fun removeItem(position: Int): List<NumberItem>
}
