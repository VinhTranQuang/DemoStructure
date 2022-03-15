package com.android.example.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.example.data.source.local.dao.TradeDao
import com.android.example.domain.model.BuyModel

@Database(entities = [BuyModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val tradeDao: TradeDao

    companion object {
        const val DB_NAME = "TradeDatabase.db"
    }
}
