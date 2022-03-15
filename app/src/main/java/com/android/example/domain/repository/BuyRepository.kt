package com.android.example.domain.repository

import com.android.example.domain.model.BuyModel
import io.reactivex.Single

/**
 * To make an interaction between [AlbumRepositoryImp] & [GetAlbumsUseCase]
 * */
interface BuyRepository {
    fun getBuys(): Single<List<BuyModel>>
}
