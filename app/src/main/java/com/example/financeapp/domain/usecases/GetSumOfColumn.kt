package com.example.financeapp.domain.usecases

import com.example.financeapp.domain.repository.FinanceRepository

class GetSumOfColumn(
    private val repository: FinanceRepository
) {
    suspend operator fun invoke():Int{
       return  repository.getSumOfColumn()
    }
}