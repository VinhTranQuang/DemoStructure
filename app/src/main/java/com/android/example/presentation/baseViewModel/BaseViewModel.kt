package com.android.example.presentation.baseViewModel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {

    private var compositeDisposables: CompositeDisposable? = null

    override fun onCleared() {
        compositeDisposables?.dispose()
        super.onCleared()
    }

    protected fun addDisposable(disposable: Disposable) {
        if (compositeDisposables == null)
            compositeDisposables = CompositeDisposable()
        compositeDisposables?.add(disposable)
    }
}