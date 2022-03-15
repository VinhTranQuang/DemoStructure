package com.android.example.presentation.callFeature

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.android.example.R
import com.android.example.databinding.ItemCallBinding
import com.android.example.domain.model.CallModel

internal class CallAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val datalist: MutableList<CallModel> = ArrayList()

    /*
     * This method is called right when adapter is created &
     * is used to initialize ViewHolders
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holderItemCallBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.item_call, parent, false
        )
        return CallViewHolder(holderItemCallBinding)
    }

    /* It is called for each ViewHolder to bind it to the adapter &
     * This is where we pass data to ViewHolder
     * */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CallViewHolder).onBind(getItem(position))
    }

    private fun getItem(position: Int): CallModel {
        return datalist[position]
    }

    /*
     * This method returns the size of collection that contains the items we want to display
     * */
    override fun getItemCount(): Int {
        return datalist.size
    }

    fun addData(list: List<CallModel>) {
        this.datalist.clear()
        this.datalist.addAll(list)
        notifyDataSetChanged()
    }

    inner class CallViewHolder(
        private val dataBinding: ViewDataBinding
    ) : RecyclerView.ViewHolder(dataBinding.root) {

        fun onBind(callObj: CallModel) {
            val holderItemCallBinding = dataBinding as ItemCallBinding
            val callObjViewModel = CallObjectViewModel(callObj)
            holderItemCallBinding.callObjViewModel = callObjViewModel

        }
    }
}
