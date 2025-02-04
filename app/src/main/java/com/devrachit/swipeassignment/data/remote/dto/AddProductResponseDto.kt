package com.devrachit.swipeassignment.data.remote.dto

data class AddProductResponseDto(
    val message: String,
    val productDetails: ProductItemDto,
    val productId: Int,
    val success: Boolean,
)
