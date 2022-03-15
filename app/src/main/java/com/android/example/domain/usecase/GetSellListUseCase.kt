package com.android.example.domain.usecase

import com.android.example.domain.model.BuyModel
import com.android.example.domain.repository.SellRepository
import com.android.example.domain.usecase.base.UseCase
import io.reactivex.Flowable
import javax.inject.Inject

class GetSellListUseCase @Inject constructor(
    private val repository: SellRepository
) : UseCase<Boolean, Flowable<List<BuyModel>>>() {

    override fun execute(param: Boolean?): Flowable<List<BuyModel>> {
        return repository.getAllSellListLocal()
    }
}
