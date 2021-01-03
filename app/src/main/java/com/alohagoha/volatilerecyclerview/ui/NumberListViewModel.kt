package com.alohagoha.volatilerecyclerview.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alohagoha.volatilerecyclerview.model.NumberItemGenerator
import com.alohagoha.volatilerecyclerview.model.entities.OperationState
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class NumberListViewModel : ViewModel() {

    private val _numberList: MutableLiveData<OperationState> =
        MutableLiveData<OperationState>(OperationState())
    val numberList: LiveData<OperationState> = _numberList

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

    fun insertSomeData() {

    }

    fun getNumbersList() = numberList

    fun removeItem(position: Int) {
        numberList.value?.let {
            _numberList.value = NumberItemGenerator.removeAt(it, position)
        }
    }
}
