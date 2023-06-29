package com.example.financeapp.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "finance_items")
data class FinanceItem(
    val type:String,
    val cost:Int,
    val category:String,
    val comment:String,
    @PrimaryKey(autoGenerate = true)
    val id:Int = UNDEFINED_ID
){
    companion object
    {
        const val UNDEFINED_ID = 0
    }
}
