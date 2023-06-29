package com.example.financeapp.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.financeapp.data.repository.FinanceRepositoryImpl
import com.example.financeapp.domain.entities.FinanceItem
import com.example.financeapp.domain.usecases.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val repository = FinanceRepositoryImpl(application)

    private val getFinanceListUseCase = GetFinanceListUseCase(repository)
    private val addFinanceItemUseCase = AddFinanceItemUseCase(repository)
    private val getFinanceItemUseCase = GetFinanceItemUseCase(repository)
    private val editFinanceItemUseCase = EditFinanceItemUseCase(repository)
    private val deleteFinanceItemUseCase = DeleteFinanceItemUseCase(repository)
    private val getSumOfColumn = GetSumOfColumn(repository)
    private val _currentItem = MutableLiveData<FinanceItem>()

    private val _totalCost = MutableLiveData<Int>()
    val totalCost: LiveData<Int>
        get() = _totalCost


    val operationsList = getFinanceListUseCase.invoke()


    fun addOperationItem(inputType:String,inputCost:Int,inputCategory:String,inputComment:String){
        val type = inputType
        val cost = inputCost
        val category = inputCategory
        val comment = inputComment
        viewModelScope.launch(Dispatchers.IO) {
            val financeItem = FinanceItem(type,cost,category,comment)
            addFinanceItemUseCase.invoke(financeItem)
        }
    }

    fun deleteOperationItem(financeItem: FinanceItem){
        viewModelScope.launch(Dispatchers.IO) {
            deleteFinanceItemUseCase.invoke(financeItem)
        }
    }
    fun getFinanceItem(financeItemId:Int){
        viewModelScope.launch (Dispatchers.IO){
            val item = getFinanceItemUseCase.invoke(financeItemId)
            _currentItem
        }
    }

    fun editFinanceItem(inputType:String,inputCost:Int,inputCategory:String,inputComment:String){
        val type = inputType
        val cost = inputCost
        val category = inputCategory
        val comment = inputComment
        viewModelScope.launch(Dispatchers.IO) {
            val financeItem = FinanceItem(type,cost,category,comment)
            editFinanceItemUseCase.invoke(financeItem)
        }
    }
     fun getTotalSum(){
        viewModelScope.launch(Dispatchers.IO) {
            _totalCost.value = getSumOfColumn()!!
        }

    }




}