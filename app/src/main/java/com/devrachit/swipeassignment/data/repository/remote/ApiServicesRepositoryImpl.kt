package com.devrachit.swipeassignment.data.repository.remote

import com.devrachit.swipeassignment.data.models.ProductItem
import com.devrachit.swipeassignment.data.remote.dto.ProductItemDto
import com.devrachit.swipeassignment.data.remote.services.ApiServices
import com.devrachit.swipeassignment.domain.repository.remote.ApiServicesRepository

class ApiServicesRepositoryImpl(
    private val api: ApiServices,
) : ApiServicesRepository {
    override suspend fun getProducts(): List<ProductItemDto> = api.get()
//    {
//        return
//        withContext(Dispatchers.IO) {
//            val products = api.get()
////            Log.d("Products", "getProducts: $products ")
//            products
//        }
//    }

//    override suspend fun postProduct(
//        productName: RequestBody,
//        productType: RequestBody,
//        price: RequestBody,
//        tax: RequestBody,
//        files: List<MultipartBody.Part>,
//    ): SentResponse {
//        return withContext(Dispatchers.IO) {
//            api.add(
//                productName = productName,
//                productType = productType,
//                price = price,
//                tax = tax,
//                files = files
//            )
//        }
//    }
}