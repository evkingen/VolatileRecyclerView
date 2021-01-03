package com.alohagoha.volatilerecyclerview.interactor

import com.alohagoha.volatilerecyclerview.model.NumberItemGenerator
import com.alohagoha.volatilerecyclerview.model.entities.NumberItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class NumberListInteractorImpl : NumberListInteractor {

    fun getStartedList() = NumberItemGenerator.getStartNumberedList()


    override suspend fun addRandomPosition() =
        flow<List<NumberItem>> {
            while (true) {
                delay(5000)
                emit(NumberItemGenerator.addRandomPosition())
            }
        }

    override fun removeByPosition(position: Int) {
        NumberItemGenerator.removeAt(position)

    }
}