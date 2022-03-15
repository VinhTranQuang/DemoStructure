package com.android.example.domain.usecase

import com.android.example.domain.model.BuyModel
import com.android.example.domain.repository.BuyRepository
import com.android.example.domain.usecase.base.UseCase
import io.reactivex.Single
import javax.inject.Inject

class GetBuyListUseCase @Inject constructor(
    private val repository: BuyRepository
) : UseCase<Boolean, Single<List<BuyModel>>>() {

    override fun execute(param: Boolean?): Single<List<BuyModel>> {
        return repository.getBuys()
    }
}
