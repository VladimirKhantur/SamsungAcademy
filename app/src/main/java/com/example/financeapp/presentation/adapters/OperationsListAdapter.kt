package com.example.financeapp.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.financeapp.R
import com.example.financeapp.domain.entities.FinanceItem

class OperationsListAdapter: ListAdapter<FinanceItem, OperationsListAdapter.OperationItemViewHolder>(OperationsItemDiffCallback()) {

    var onOperationsItemClickListener: ((FinanceItem) -> Unit)? = null

    class OperationItemViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tv_name)
        val tvCash = view.findViewById<TextView>(R.id.tv_cash)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperationItemViewHolder {
        val layout = R.layout.item_operations_rv
        val view = LayoutInflater.from(parent.context).inflate(layout,parent,false)
        return OperationItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: OperationItemViewHolder, position: Int) {
        val operationItem = getItem(position)
        viewHolder.itemView.setOnClickListener {
            onOperationsItemClickListener?.invoke((operationItem))
        }
        viewHolder.tvName.text = operationItem.category
        viewHolder.tvCash.text = operationItem.cost.toString()
    }

    companion object {

        const val VIEW_TYPE_ENABLED = 100
        const val MAX_POOL_SIZE = 30
    }
}