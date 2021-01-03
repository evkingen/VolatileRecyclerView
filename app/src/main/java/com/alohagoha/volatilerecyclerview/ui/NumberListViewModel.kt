package com.alohagoha.volatilerecyclerview.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alohagoha.volatilerecyclerview.model.NumberItemGenerator
import com.alohagoha.volatilerecyclerview.model.entities.NumberItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class NumberListViewModel : ViewModel() {

    private val _numberList: MutableLiveData<List<NumberItem>> =
        MutableLiveData<List<NumberItem>>(NumberItemGenerator.getStartNumberedList(15))
    val numberList: LiveData<List<NumberItem>> = _numberList

    init {
        viewModelScope.launch {
            while (isActive) {
                delay(5000L)
                numberList.value?.let {
                    _numberList.value = NumberItemGenerator.addRandomPosition(it)
                }
            }
        }
    }

    fun removeItem(position: Int) {
        numberList.value?.let {
            _numberList.value = NumberItemGenerator.removeAt(it, position)
        }
    }
}
