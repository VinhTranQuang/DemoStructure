package com.android.example.presentation.baseFragment

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.example.MainApplication
import com.android.example.presentation.baseViewModel.BaseViewModel
import com.android.example.util.LocaleManager
import java.lang.reflect.ParameterizedType
import androidx.lifecycle.ViewModelProviders


abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    private var realBinding: B? = null

    protected val mViewDataBinding: B get() = realBinding ?: throw IllegalStateException("Trying to access the binding outside of the view lifecycle.")

    protected lateinit var mViewModel: VM

    private var mInitialized = true
    protected var mViewCreated = false
    private var mViewDestroyed = false
    protected lateinit var contextLanguage: Context
    open fun useSystemLanguage() = false
    open fun onChangedLanguage(contextAfterChangedLanguage: Context) {}

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val viewModelClass = getViewModelClass()
        mViewModel= activity?.run {
            ViewModelProvider(this).get(viewModelClass!!)
        }!!
        contextLanguage = context
        val localeManager = LocaleManager.getInstance()
        val languageCode = getLanguageCode(context)
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N)
            super.onAttach(
                localeManager.setLocaleWithCreateNewContext(
                    context, languageCode))
        else {
            localeManager.setLocaleWithUpdateConfiguration(context, languageCode)
            super.onAttach(context)
        }
    }
    @LayoutRes
    protected abstract fun getLayoutId(): Int

    @Suppress("UNCHECKED_CAST")
    protected open fun getViewModelClass(): Class<VM>? {
        return (javaClass.genericSuperclass as ParameterizedType)
            .actualTypeArguments[1] as Class<VM>
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<B>(inflater, getLayoutId(), container, false).also {
        realBinding = it
    }.root


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding.lifecycleOwner = viewLifecycleOwner
        mViewCreated = true
        mViewDestroyed = false
        requireActivity().apply {
            MainApplication.get(this)!!.appLanguage.observe(this@BaseFragment.viewLifecycleOwner, Observer {
                if(!it.isNullOrEmpty()){
                    val localeContext = LocaleManager.getInstance().setLocaleWithCreateNewContext(applicationContext, it)
                    contextLanguage = localeContext
                    onChangedLanguage(localeContext)
                }
            })
        }
    }

    override fun onDestroyView() {
        mViewDestroyed = true
        mViewCreated = false
        realBinding?.unbind()
        super.onDestroyView()
        realBinding = null
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    open fun onAfterInitialized() {
    }

    open fun onInitialized() {
        mInitialized = false
    }

    fun isInitialized(): Boolean {
        return mInitialized
    }

    fun isViewCreated(): Boolean {
        return mViewCreated
    }
    private fun getLanguageCode(context: Context): String {
//        return if(!useSystemLanguage())
//            ""
//            SharePreferenceManager.getInstance(context).languageCode
//        else LocaleUtils.getDefaultLanguage()
        return ""
    }

}