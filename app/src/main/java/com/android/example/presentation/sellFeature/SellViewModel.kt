package com.android.example.presentation.sellFeature

import androidx.lifecycle.MutableLiveData
import com.android.example.domain.model.BuyModel
import com.android.example.domain.usecase.GetBuyListUseCase
import com.android.example.domain.usecase.GetSellListUseCase
import com.android.example.domain.usecase.InsertSellListUseCase
import com.android.example.presentation.baseViewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**To store & manage UI-related data in a lifecycle conscious way!
 * this class allows data to survive configuration changes such as screen rotation
 * by interacting with [GetBuyListUseCase]
 *
 * */
@HiltViewModel
class SellViewModel @Inject constructor(private val insertSellListUseCase: InsertSellListUseCase, private val getSellListUseCase: GetSellListUseCase) : BaseViewModel() {

    val sellListData = MutableLiveData<List<BuyModel>>()
    val isLoading = MutableLiveData<Boolean>()

    init {
        isLoading.value = false
    }

    fun insertSellList() {
        // I dont know that the data to save to local is fetched from server or hard code with your requirement so i do hard code to save to local database for convenience and fast
        var sellList = arrayListOf<BuyModel>()
        val sellModel1 = BuyModel(name = "MacBook Pro", id = 1, price = 205000, quantity = 2, type = 1)
        val sellModel2 = BuyModel(name = "MacBook Air", id = 2, price = 105000, quantity = 5, type = 1)
        val sellModel3 = BuyModel(name = "MacBook M1", id = 3, price = 305000, quantity = 10, type = 1)
        sellList.add(sellModel1)
        sellList.add(sellModel2)
        sellList.add(sellModel3)
        addDisposable(
            insertSellListUseCase.execute(sellList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                }
                .doFinally {
                }
                .subscribe({
                    getAllSellListFromLocal()
                }, {
                })
        )
    }
    fun getAllSellListFromLocal() {
        addDisposable(
            getSellListUseCase.execute(false)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                }
                .doFinally {
                }
                .subscribe({
                    isLoading.value = true
                    sellListData.value = it
                }, {
                })
        )
    }
}
