package com.android.example.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ItemToSell")
data class BuyModel(
    @PrimaryKey
    var id: Int,
    var name: String,
    var price: Long,
    var quantity: Long,
    var type: Int
    )
