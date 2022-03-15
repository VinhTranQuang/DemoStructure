package com.android.example.presentation.callFeature

import androidx.lifecycle.MutableLiveData
import com.android.example.domain.model.CallModel

/**A helper class for the UI controller that is responsible for
 * preparing data for [CallObjectViewModel] as the UI
 *
 * */
class CallObjectViewModel(call: CallModel) {

    private val TAG = CallObjectViewModel::class.java.simpleName
    val callData = MutableLiveData<CallModel>()

    init {
        callData.value = call
    }
}
