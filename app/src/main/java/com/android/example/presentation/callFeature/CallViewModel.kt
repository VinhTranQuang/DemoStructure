package com.android.example.presentation.callFeature

import androidx.lifecycle.MutableLiveData
import com.android.example.domain.model.CallModel
import com.android.example.domain.usecase.GetCallListUseCase
import com.android.example.presentation.baseViewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CallViewModel @Inject constructor(
    private val getCallListUseCase: GetCallListUseCase
)
    : BaseViewModel() {

    val callListData = MutableLiveData<List<CallModel>>()
    val isLoading = MutableLiveData<Boolean>()

    init {
        isLoading.value = false
    }

    fun getCallList() {
        addDisposable(
            getCallListUseCase.execute(false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                }
                .doFinally {
                }
                .subscribe({
                    isLoading.value = true
                    callListData.value = it
                }, {
                })
        )
    }
}
