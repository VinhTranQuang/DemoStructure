package com.android.example.domain.usecase

import com.android.example.domain.model.CallModel
import com.android.example.domain.repository.CallRepository
import com.android.example.domain.usecase.base.UseCase
import io.reactivex.Single
import javax.inject.Inject

class GetCallListUseCase @Inject constructor(
    private val repository: CallRepository
) : UseCase<Boolean, Single<List<CallModel>>>() {

    override fun execute(param: Boolean?): Single<List<CallModel>> {
        return repository.getCalls()
    }
}
