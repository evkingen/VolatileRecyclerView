package com.alohagoha.volatilerecyclerview.model.entities

data class NumberItem(val number: Int) : Comparable<NumberItem> {
    override fun compareTo(other: NumberItem): Int = this.number.compareTo(other.number)

}
