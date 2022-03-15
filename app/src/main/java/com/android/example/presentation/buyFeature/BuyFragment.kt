package com.android.example.presentation.buyFeature

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.android.example.R
import com.android.example.databinding.FragmentBuyBinding
import com.android.example.domain.model.BuyModel
import com.android.example.presentation.baseFragment.BaseFragment
import com.android.example.presentation.baseFragment.OnBackPressCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BuyFragment : BaseFragment<FragmentBuyBinding, BuyViewModel>(), OnBackPressCallback {

    private var adapter: BuyAdapter? = null
    companion object {

        val FRAGMENT_NAME = BuyFragment::class.java.name

        @JvmStatic
        fun newInstance() =
            BuyFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_buy
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = BuyAdapter()
        mViewModel.getBuyList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding.apply {
            buysViewModel = mViewModel
            recyclerView.adapter = adapter
            viewHandler = this@BuyFragment
        }
        mViewModel.isLoading.observe(
            viewLifecycleOwner,
            Observer {
                it?.let { visibility ->
                    mViewDataBinding.buyProgressBar.progressBar.visibility = if (visibility) View.GONE else View.VISIBLE
                }
            }
        )

        mViewModel.buyListData.observe(
            viewLifecycleOwner,
            Observer {
                it?.let {
                    initRecyclerView(it)
                }
            }
        )
    }

    private fun initRecyclerView(list: List<BuyModel>) {
        adapter?.addData(list)
    }

    override fun onDetach() {
        super.onDetach()
        adapter = null
    }

    override fun onChangedLanguage(contextAfterChangedLanguage: Context) {

    }

    override fun onButtonBackClicked() {
        activity?.onBackPressed()
    }
}
