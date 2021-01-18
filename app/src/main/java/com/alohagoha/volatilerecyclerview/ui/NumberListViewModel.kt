package com.alohagoha.volatilerecyclerview.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alohagoha.volatilerecyclerview.model.entities.NumberItem
import com.alohagoha.volatilerecyclerview.model.repositories.INumberRepo
import com.alohagoha.volatilerecyclerview.model.repositories.NumberRepo
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class NumberListViewModel(
        private val repo: INumberRepo = NumberRepo()
) : ViewModel(), INumberListViewModel {
    override val numberList: MutableLiveData<List<NumberItem>> = MutableLiveData<List<NumberItem>>()

    init {
        viewModelScope.launch {
            while (true) {
                repo.addItemToRandomPosition()
            }
        }

        repo.stateNumbers.onEach {
            numberList.value = it
        }.launchIn(viewModelScope)

    }

    override fun removeItem(position: Int) {
        viewModelScope.launch {
            repo.removeItem(position)
        }
    }

}
