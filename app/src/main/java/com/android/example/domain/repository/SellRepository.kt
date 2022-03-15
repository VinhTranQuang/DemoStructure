package com.android.example.domain.repository

import com.android.example.domain.model.BuyModel
import io.reactivex.Completable
import io.reactivex.Flowable

interface SellRepository {
    fun insertSell(sell: ArrayList<BuyModel>): Completable
    fun getAllSellListLocal(): Flowable<List<BuyModel>>
}
