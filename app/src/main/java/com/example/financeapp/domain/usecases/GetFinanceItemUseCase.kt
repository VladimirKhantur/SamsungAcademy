package com.example.financeapp.domain.usecases

import com.example.financeapp.domain.entities.FinanceItem
import com.example.financeapp.domain.repository.FinanceRepository

class GetFinanceItemUseCase(
    private val repository: FinanceRepository
    ) {
    suspend operator fun invoke(financeItemId: Int): FinanceItem {
        return repository.getFinanceItemById(financeItemId)
    }
}