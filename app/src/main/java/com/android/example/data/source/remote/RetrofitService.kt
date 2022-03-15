package com.android.example.data.source.remote

import com.android.example.domain.model.BuyModel
import com.android.example.domain.model.CallModel
import io.reactivex.Single
import retrofit2.http.GET

interface RetrofitAPIService {

    @GET("call")
    fun getCalls(): Single<List<CallModel>>

    @GET("buy")
    fun getBuys(): Single<List<BuyModel>>
}
