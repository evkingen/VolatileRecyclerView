package com.alohagoha.volatilerecyclerview.model.repositories

import com.alohagoha.volatilerecyclerview.model.entities.NumberItem
import kotlinx.coroutines.flow.StateFlow

interface INumberRepo {
    val stateNumbers: StateFlow<List<NumberItem>>
    suspend fun removeItem(position: Int)
    suspend fun addItemToRandomPosition()
}
