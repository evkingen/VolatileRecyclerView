package com.alohagoha.volatilerecyclerview.model.entities

data class OperationState(
    val listItem: List<NumberItem> = listOf(),
    val position: Int = -1,
    val operation: OperationType = OperationType.NOTHING
)