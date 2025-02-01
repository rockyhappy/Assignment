package com.devrachit.swipeassignment.data.remote.dto

import com.devrachit.swipeassignment.data.models.ProductItem
import kotlinx.serialization.SerialName

data class ProductItemDto(
    val image : String = "",
    val price: Double = 0.0,
    val product_name: String = "",
    val product_type: String = "",
    val tax: Double = 0.0,
)
fun ProductItemDto.toEntity() = ProductItem(
    image = image,
    price = price,
    productName = product_name,
    productType = product_type,
    tax = tax
)
