package com.example.financeapp.domain.usecases

import androidx.lifecycle.LiveData
import com.example.financeapp.domain.entities.FinanceItem
import com.example.financeapp.domain.repository.FinanceRepository

class GetFinanceListUseCase(
    private val repository: FinanceRepository
    ){
    operator fun invoke(): LiveData<List<FinanceItem>> {
        return repository.getListFinanceItem()
    }
}