package com.android.example.presentation.buyFeature

import androidx.lifecycle.MutableLiveData
import com.android.example.domain.model.BuyModel
import com.android.example.domain.usecase.GetBuyListUseCase
import com.android.example.presentation.baseViewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class BuyViewModel @Inject constructor(private val getBuyListUseCase: GetBuyListUseCase) : BaseViewModel() {

    val buyListData = MutableLiveData<List<BuyModel>>()
    val isLoading = MutableLiveData<Boolean>()

    init {
        isLoading.value = false
    }

    fun getBuyList() {
        addDisposable(
            getBuyListUseCase.execute(false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                }
                .doFinally {
                }
                .subscribe({
                    isLoading.value = true
                    buyListData.value = it
                }, {
                })
        )
    }
}
