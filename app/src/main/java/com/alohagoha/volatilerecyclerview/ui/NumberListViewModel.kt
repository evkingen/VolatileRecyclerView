package com.alohagoha.volatilerecyclerview.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alohagoha.volatilerecyclerview.model.entities.NumberItem
import com.alohagoha.volatilerecyclerview.model.repositories.INumberRepo
import com.alohagoha.volatilerecyclerview.model.repositories.NumberRepo
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NumberListViewModel(
    private val repo: INumberRepo = NumberRepo(
        mutableListOf(),
        mutableListOf()
    )
) : ViewModel() {
    private val _numberList: MutableLiveData<List<NumberItem>> =
        MutableLiveData<List<NumberItem>>()
    val numberList: LiveData<List<NumberItem>>
        get() = _numberList

    init {
        _numberList.value = repo.initStartList(15)
        viewModelScope.launch {
            repo.getNumbersList().collect { _numberList.value = it }
        }
    }

    fun removeItem(position: Int) {
        _numberList.value = repo.removeItem(position)
    }

}
