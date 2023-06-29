package com.example.financeapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.financeapp.domain.entities.FinanceItem

class OperationsItemDiffCallback:DiffUtil.ItemCallback<FinanceItem>(){
    override fun areItemsTheSame(oldItem: FinanceItem, newItem: FinanceItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FinanceItem, newItem: FinanceItem): Boolean {
        return oldItem == newItem
    }
}