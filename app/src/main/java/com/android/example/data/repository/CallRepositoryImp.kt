package com.android.example.data.repository

import com.android.example.data.source.remote.RetrofitAPIService
import com.android.example.domain.model.CallModel
import com.android.example.domain.repository.CallRepository
import io.reactivex.Single

class CallRepositoryImp(
    private val retrofitService: RetrofitAPIService
) : CallRepository {

    override fun getCalls(): Single<List<CallModel>> {
        return retrofitService.getCalls()
    }
}
