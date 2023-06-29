package com.example.financeapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.financeapp.data.database.AppDataBase
import com.example.financeapp.data.database.FinanceDao
import com.example.financeapp.domain.entities.FinanceItem
import com.example.financeapp.domain.repository.FinanceRepository
import kotlinx.coroutines.flow.Flow

class FinanceRepositoryImpl(
    application: Application
    ):FinanceRepository
{
    private val dao = AppDataBase.getInstance(application).financeListDao()
    override suspend fun addFinanceItem(financeItem: FinanceItem) {
        return dao.addFinanceItem(financeItem)
    }

    override suspend fun deleteFinanceItem(financeItem: FinanceItem) {
        return dao.deleteFinanceItem(financeItem)
    }

    override suspend fun editFinanceItem(financeItem: FinanceItem) {
        return dao.updateFinanceItem(financeItem)
    }

    override suspend fun getFinanceItemById(financeItemId: Int): FinanceItem {
        return dao.getFinanceItemById(financeItemId)
    }

    override fun getListFinanceItem(): LiveData<List<FinanceItem>> {
        return dao.getFinanceList()
    }

    override suspend fun getSumOfColumn(): Int {
        return dao.getSumOfColumn()
    }


}