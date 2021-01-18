package com.alohagoha.volatilerecyclerview.ui

import androidx.lifecycle.LiveData
import com.alohagoha.volatilerecyclerview.model.entities.NumberItem

interface INumberListViewModel {
    val numberList: LiveData<List<NumberItem>>

    fun removeItem(position: Int)
}
