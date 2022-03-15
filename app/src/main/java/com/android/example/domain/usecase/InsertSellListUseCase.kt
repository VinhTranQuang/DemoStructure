package com.android.example.domain.usecase

import com.android.example.domain.model.BuyModel
import com.android.example.domain.repository.SellRepository
import com.android.example.domain.usecase.base.UseCase
import io.reactivex.Completable
import javax.inject.Inject

class InsertSellListUseCase @Inject constructor(
    private val repository: SellRepository
) : UseCase<ArrayList<BuyModel>, Completable>() {

    override fun execute(param: ArrayList<BuyModel>?): Completable {
        param?.let {
            return repository.insertSell(param)
        }
        return Completable.error(Throwable("Param is null"))
    }
}

