package com.devrachit.swipeassignment.domain.repository.local

import com.devrachit.swipeassignment.data.models.ProductItem
import kotlinx.coroutines.flow.Flow

interface LocalProductsRepository {
    suspend fun upsertProductEntity(entity: ProductItem)
    suspend fun upsertProductList(entities: List<ProductItem>)
    suspend fun deleteProduct(entity: ProductItem)
    suspend fun fetchProductById(id: Int): Flow<ProductItem>
    suspend fun fetchAllProducts(): Flow<List<ProductItem>>
    suspend fun isTableEmpty(): Flow<Int>
    suspend fun clearTable()
    suspend fun fetchProductsByType(productType: String): Flow<List<ProductItem>>
    suspend fun fetchProductsByName(productName: String): Flow<List<ProductItem>>
    suspend fun fetchProductsByPrice(price: Double): Flow<List<ProductItem>>
    suspend fun fetchProductsByTax(tax: Double): Flow<List<ProductItem>>
    suspend fun fetchProductsByNameAndType(
        productName: String,
        productType: String
    ): Flow<List<ProductItem>>

    suspend fun fetchProductsByNameAndPrice(
        productName: String,
        price: Double
    ): Flow<List<ProductItem>>

    suspend fun fetchProductsByNameAndTax(productName: String, tax: Double): Flow<List<ProductItem>>
    suspend fun fetchProductsByTypeAndPrice(
        productType: String,
        price: Double
    ): Flow<List<ProductItem>>

    suspend fun fetchProductsByTypeAndTax(productType: String, tax: Double): Flow<List<ProductItem>>
    suspend fun fetchProductsByPriceAndTax(price: Double, tax: Double): Flow<List<ProductItem>>
}