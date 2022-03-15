package com.android.example.presentation.sellFeature

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.android.example.R
import com.android.example.databinding.FragmentSellBinding
import com.android.example.domain.model.BuyModel
import com.android.example.presentation.baseFragment.BaseFragment
import com.android.example.presentation.baseFragment.OnBackPressCallback
import com.android.example.presentation.buyFeature.BuyAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SellFragment : BaseFragment<FragmentSellBinding, SellViewModel>(), OnBackPressCallback {

    private var adapter: BuyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = BuyAdapter()
        mViewModel.insertSellList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding.apply {
            sellsViewModel = mViewModel
            recyclerView.adapter = adapter
            viewHandler = this@SellFragment
        }
        mViewModel.isLoading.observe(
            viewLifecycleOwner,
            Observer {
                it?.let { visibility ->
                    mViewDataBinding.sellProgressBar.progressBar.visibility = if (visibility) View.GONE else View.VISIBLE
                }
            }
        )

        mViewModel.sellListData.observe(
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

    companion object {

        val FRAGMENT_NAME = SellFragment::class.java.name

        @JvmStatic
        fun newInstance() =
            SellFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_sell
    }
    override fun onChangedLanguage(contextAfterChangedLanguage: Context) {

    }

    override fun onButtonBackClicked() {
        activity?.onBackPressed()
    }
}
