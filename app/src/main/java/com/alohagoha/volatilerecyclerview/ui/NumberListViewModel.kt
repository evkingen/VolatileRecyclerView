package com.alohagoha.volatilerecyclerview.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alohagoha.volatilerecyclerview.interactor.NumberListInteractorImpl
import com.alohagoha.volatilerecyclerview.model.NumberItemGenerator
import com.alohagoha.volatilerecyclerview.model.entities.NumberItem
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NumberListViewModel : ViewModel() {
    private val interactor = NumberListInteractorImpl()
    private val _numberList: MutableLiveData<List<NumberItem>> =
        MutableLiveData<List<NumberItem>>()
    val numberList: LiveData<List<NumberItem>>
        get() = _numberList

    init {
        viewModelScope.launch {
            interactor.addRandomPosition().collect {
                _numberList.value = it
            }
        }
    }

    fun removeItem(position: Int) {
        _numberList.value = NumberItemGenerator.removeAt(position)
    }

}
