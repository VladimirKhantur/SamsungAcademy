package com.example.financeapp.domain.repository

import androidx.lifecycle.LiveData
import com.example.financeapp.domain.entities.FinanceItem
import kotlinx.coroutines.flow.Flow

interface FinanceRepository {
    suspend fun addFinanceItem(financeItem: FinanceItem)

    suspend fun deleteFinanceItem(financeItem: FinanceItem)

    suspend fun editFinanceItem(financeItem: FinanceItem)

    suspend fun getFinanceItemById(financeItemId: Int): FinanceItem

    fun getListFinanceItem():LiveData<List<FinanceItem>>

    suspend fun getSumOfColumn(): Int
}