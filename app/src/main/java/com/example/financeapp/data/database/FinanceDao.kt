package com.example.financeapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.financeapp.domain.entities.FinanceItem
import kotlinx.coroutines.flow.Flow

@Dao
interface FinanceDao {
    @Query("SELECT * FROM finance_items")
    fun getFinanceList():LiveData<List<FinanceItem>>

    @Query("SELECT * FROM finance_items WHERE id = :id")
    fun getFinanceItemById(id:Int):FinanceItem

    @Query("SELECT SUM(cost) FROM finance_items")
    fun getSumOfColumn(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun addFinanceItem(financeItemDbModel: FinanceItem)

    @Delete
     fun deleteFinanceItem(financeItemDbModel: FinanceItem)

    @Update
     fun updateFinanceItem(financeItemDbModel: FinanceItem)


}