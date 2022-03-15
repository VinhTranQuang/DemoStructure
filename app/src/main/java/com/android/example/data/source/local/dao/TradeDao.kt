package com.android.example.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.android.example.domain.model.BuyModel
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface TradeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSell(sellList: ArrayList<BuyModel>): Completable

    @Query("SELECT * FROM ItemToSell")
    fun getAllSellList(): Flowable<List<BuyModel>>
}
