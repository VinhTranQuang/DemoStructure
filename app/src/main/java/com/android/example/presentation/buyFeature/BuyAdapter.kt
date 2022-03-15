package com.android.example.presentation.buyFeature

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.android.example.R
import com.android.example.databinding.ItemBuyBinding
import com.android.example.domain.model.BuyModel

internal class BuyAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList: MutableList<BuyModel> = ArrayList()

    /*
     * This method is called right when adapter is created &
     * is used to initialize ViewHolders
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderBuyBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.item_buy, parent, false
        )
        return BuyViewHolder(holderBuyBinding)
    }

    /* It is called for each ViewHolder to bind it to the adapter &
     * This is where we pass data to ViewHolder
     * */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BuyViewHolder).onBind(getItem(position))
    }

    private fun getItem(position: Int): BuyModel {
        return dataList[position]
    }

    /*
     * This method returns the size of collection that contains the items we want to display
     * */
    override fun getItemCount(): Int {
        return dataList.size
    }

    fun addData(list: List<BuyModel>) {
        this.dataList.clear()
        this.dataList.addAll(list)
        notifyDataSetChanged()
    }

    inner class BuyViewHolder(
        private val dataBinding: ViewDataBinding
    ) : RecyclerView.ViewHolder(dataBinding.root) {

        fun onBind(buyObj: BuyModel) {
            val holderBuyBinding = dataBinding as ItemBuyBinding
            val buyObjViewModel = BuyObjectViewModel(buyObj)
            holderBuyBinding.buyObjViewModel = buyObjViewModel
        }
    }
}
