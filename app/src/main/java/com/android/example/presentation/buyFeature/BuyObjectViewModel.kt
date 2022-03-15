package com.android.example.presentation.buyFeature

import androidx.lifecycle.MutableLiveData
import com.android.example.domain.model.BuyModel

class BuyObjectViewModel(val call: BuyModel) {

    private val TAG = BuyObjectViewModel::class.java.simpleName
    val callData = MutableLiveData<BuyModel>()

    init {
        callData.value = call
    }
}
