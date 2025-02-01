package com.devrachit.swipeassignment.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ProductItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val image: String = "",
    val price: Double = 0.0,
    val productName: String = "",
    val productType: String = "",
    val tax: Double = 0.0,
)