package com.android.example.presentation.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import com.android.example.R
import com.android.example.databinding.FragmentMainBinding
import com.android.example.presentation.baseFragment.BaseFragment
import com.android.example.presentation.buyFeature.BuyFragment
import com.android.example.presentation.callFeature.CallFragment
import com.android.example.presentation.sellFeature.SellFragment
import com.android.example.util.replaceFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentMainBinding, HomeViewModel>(), OnClickCallback {

    companion object {

        val FRAGMENT_NAME = HomeFragment::class.java.name

        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding.apply {
            viewHandler = this@HomeFragment
        }
    }

    override fun onChangedLanguage(contextAfterChangedLanguage: Context) {

    }

    fun replaceSingleFragment(view: View) {
        when (view.id) {
            R.id.btnCall -> {
                clearBackStack()
                (requireActivity() as MainActivity).run {
                    replaceFragment(
                        R.id.frame_container,
                        CallFragment.newInstance(),
                        CallFragment.FRAGMENT_NAME,
                        true
                    )
                }
            }
            R.id.btnBuy -> {
                clearBackStack()
                (requireActivity() as MainActivity).run {
                    replaceFragment(
                        R.id.frame_container,
                        BuyFragment.newInstance(),
                        BuyFragment.FRAGMENT_NAME,
                        true
                    )
                }
            }
            R.id.btnSell -> {
                clearBackStack()
                (requireActivity() as MainActivity).run {
                    replaceFragment(
                        R.id.frame_container,
                        SellFragment.newInstance(),
                        SellFragment.FRAGMENT_NAME,
                        true
                    )
                }
            }
        }
    }

    override fun onButtonClick(view: View) {
        replaceSingleFragment(view)
    }
    fun clearBackStack(){
        val fm: FragmentManager = activity!!.supportFragmentManager
        for (i in 0 until fm.getBackStackEntryCount()) {
            fm.popBackStack()
        }
    }

}
