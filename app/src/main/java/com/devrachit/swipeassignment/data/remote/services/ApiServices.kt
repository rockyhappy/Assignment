package com.devrachit.swipeassignment.data.remote.services

import com.devrachit.swipeassignment.data.remote.dto.AddProductResponseDto
import com.devrachit.swipeassignment.data.remote.dto.ProductItemDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiServices {

    @GET("get")
    suspend fun get(): List<ProductItemDto>


    @POST("add")
    @Multipart
    suspend fun add(
        @Part("product_name") productName: RequestBody,
        @Part("product_type") productType: RequestBody,
        @Part("price") price: RequestBody,
        @Part("tax") tax: RequestBody,
        @Part files: List<MultipartBody.Part>,
    ): AddProductResponseDto

}