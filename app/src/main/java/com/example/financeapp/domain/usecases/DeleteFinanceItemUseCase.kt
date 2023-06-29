package com.example.financeapp.domain.usecases

import com.example.financeapp.domain.entities.FinanceItem
import com.example.financeapp.domain.repository.FinanceRepository

class DeleteFinanceItemUseCase(
    private val repository: FinanceRepository
    ) {
    suspend operator fun invoke(financeItem: FinanceItem){
        repository.deleteFinanceItem(financeItem)
    }
}