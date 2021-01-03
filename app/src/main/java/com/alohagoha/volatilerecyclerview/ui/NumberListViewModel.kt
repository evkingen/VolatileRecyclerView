package com.alohagoha.volatilerecyclerview.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alohagoha.volatilerecyclerview.model.entities.NumberItem
import com.alohagoha.volatilerecyclerview.model.repositories.NumberRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NumberListViewModel : ViewModel() {
    private val repo = NumberRepo()
    private val _numberList: MutableLiveData<List<NumberItem>> =
        MutableLiveData<List<NumberItem>>()
    val numberList: LiveData<List<NumberItem>>
        get() = _numberList

    init {
        repo.initStartList(15)
        _numberList.value = repo.curList
        viewModelScope.launch {
            while (true) {
                delay(5000L)
                repo.addRandomPosition()
                _numberList.value = repo.curList
            }
        }
    }

    fun removeItem(position: Int) {
        repo.removeAt(position)
        _numberList.value = repo.curList
    }

}
