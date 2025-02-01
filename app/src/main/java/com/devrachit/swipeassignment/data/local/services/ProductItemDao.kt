package com.devrachit.swipeassignment.data.local.services

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.devrachit.swipeassignment.data.models.ProductItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductItemDao {
    @Upsert
    suspend fun upsertProduct(entity: ProductItem)

    @Upsert
    suspend fun upsertProductList(entities: List<ProductItem>)

    @Delete
    suspend fun deleteProduct(entity: ProductItem)

    @Query("select * from ProductItem where id = :id")
    fun fetchProductById(id: Int): Flow<ProductItem>

    @Query("select * from ProductItem")
    fun fetchAllProducts(): Flow<List<ProductItem>>

    @Query("select Count(*) from ProductItem")
    fun isTableEmpty(): Flow<Int>

    @Query("delete from ProductItem")
    fun clearTable()

    @Query("select * from ProductItem where productType = :productType")
    fun fetchProductsByType(productType: String): Flow<List<ProductItem>>

    @Query("select * from ProductItem where productName = :productName")
    fun fetchProductsByName(productName: String): Flow<List<ProductItem>>

    @Query("select * from ProductItem where price = :price")
    fun fetchProductsByPrice(price: Double): Flow<List<ProductItem>>

    @Query("select * from ProductItem where tax = :tax")
    fun fetchProductsByTax(tax: Double): Flow<List<ProductItem>>

    @Query("select * from ProductItem where productName = :productName and productType = :productType")
    fun fetchProductsByNameAndType(productName: String, productType: String): Flow<List<ProductItem>>

    @Query("select * from ProductItem where productName = :productName and price = :price")
    fun fetchProductsByNameAndPrice(productName: String, price: Double): Flow<List<ProductItem>>

    @Query("select * from ProductItem where productName = :productName and tax = :tax")
    fun fetchProductsByNameAndTax(productName: String, tax: Double): Flow<List<ProductItem>>

    @Query("select * from ProductItem where productType = :productType and price = :price")
    fun fetchProductsByTypeAndPrice(productType: String, price: Double): Flow<List<ProductItem>>

    @Query("select * from ProductItem where productType = :productType and tax = :tax")
    fun fetchProductsByTypeAndTax(productType: String, tax: Double): Flow<List<ProductItem>>

    @Query("select * from ProductItem where price = :price and tax = :tax")
    fun fetchProductsByPriceAndTax(price: Double, tax: Double): Flow<List<ProductItem>>

}