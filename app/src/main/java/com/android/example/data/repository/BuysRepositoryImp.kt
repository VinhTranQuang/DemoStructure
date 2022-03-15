package com.android.example.data.repository

import com.android.example.data.source.remote.RetrofitAPIService
import com.android.example.domain.model.BuyModel
import com.android.example.domain.repository.BuyRepository
import io.reactivex.Single

class BuysRepositoryImp(
    private val retrofitService: RetrofitAPIService
) : BuyRepository {

    override fun getBuys(): Single<List<BuyModel>> {
        return retrofitService.getBuys()
    }
}
