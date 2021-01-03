package com.alohagoha.volatilerecyclerview.interactor

import com.alohagoha.volatilerecyclerview.model.entities.NumberItem
import kotlinx.coroutines.flow.Flow

interface NumberListInteractor {
    suspend fun addRandomPosition(): Flow<List<NumberItem>>
    fun removeByPosition(position: Int)
}
