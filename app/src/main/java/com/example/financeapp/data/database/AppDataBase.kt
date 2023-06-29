package com.example.financeapp.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.financeapp.domain.entities.FinanceItem

@Database(entities = [FinanceItem::class],version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase(){
    abstract fun financeListDao():FinanceDao

    companion object{
        private var INSTANCE:AppDataBase?=null
        private val LOCK = Any()
        private const val DB_NAME = "financeItem.db"

        fun getInstance(application: Application):AppDataBase{
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK){
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDataBase::class.java,
                    DB_NAME
                ).build()

                INSTANCE = db
                return db
            }
        }
    }


}