package com.android.example.presentation.sellFeature

import androidx.lifecycle.MutableLiveData
import com.android.example.domain.model.CallModel

/**A helper class for the UI controller that is responsible for
 * preparing data for [SellObjectViewModel] as the UI
 *
 * */
class SellObjectViewModel(val call: CallModel) {

    private val TAG = SellObjectViewModel::class.java.simpleName
    val callData = MutableLiveData<CallModel>()

    init {
        callData.value = call
    }
}
