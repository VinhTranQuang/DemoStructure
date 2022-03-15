package com.android.example.domain.repository

import com.android.example.domain.model.CallModel
import io.reactivex.Single

interface CallRepository {
    fun getCalls(): Single<List<CallModel>>
}
