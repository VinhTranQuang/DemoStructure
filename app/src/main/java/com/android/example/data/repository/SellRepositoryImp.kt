package com.android.example.data.repository

import com.android.example.data.source.local.AppDatabase
import com.android.example.data.source.remote.RetrofitAPIService
import com.android.example.domain.model.BuyModel
import com.android.example.domain.repository.SellRepository
import io.reactivex.Completable
import io.reactivex.Flowable

class SellRepositoryImp(
    private val database: AppDatabase,
    private val retrofitService: RetrofitAPIService
) : SellRepository {

    override fun getAllSellListLocal(): Flowable<List<BuyModel>> {
        return database.tradeDao.getAllSellList()
    }

    override fun insertSell(sellList: ArrayList<BuyModel>): Completable {
            return database.tradeDao.insertSell(sellList)
    }
}
