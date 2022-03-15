package com.android.example.presentation.callFeature

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.android.example.R
import com.android.example.databinding.FragmentCallBinding
import com.android.example.domain.model.CallModel
import com.android.example.presentation.baseFragment.BaseFragment
import com.android.example.presentation.baseFragment.OnBackPressCallback
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CallFragment : BaseFragment<FragmentCallBinding, CallViewModel>(), OnBackPressCallback {

    private var adapter: CallAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = CallAdapter()
        mViewModel.getCallList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding.apply {
            callsViewModel = mViewModel
            recyclerView.adapter = adapter
            viewHandler = this@CallFragment
        }
        mViewModel.isLoading.observe(
            viewLifecycleOwner,
            Observer {
                it?.let { visibility ->
                    mViewDataBinding.callProgressBar.progressBar.visibility = if (visibility) View.GONE else View.VISIBLE
                }
            }
        )

        mViewModel.callListData.observe(
            viewLifecycleOwner,
            Observer {
                it?.let {
                    initRecyclerView(it)
                }
            }
        )
    }

    private fun initRecyclerView(list: List<CallModel>) {
        adapter?.addData(list)
    }

    override fun onDetach() {
        super.onDetach()
        adapter = null
    }

    companion object {

        val FRAGMENT_NAME = CallFragment::class.java.name

        @JvmStatic
        fun newInstance() =
            CallFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_call
    }
    override fun onChangedLanguage(contextAfterChangedLanguage: Context) {

    }

    override fun onButtonBackClicked() {
        activity?.onBackPressed()
    }
}
