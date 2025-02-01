package com.devrachit.swipeassignment.data.repository.local

import com.devrachit.swipeassignment.data.local.services.ProductItemDao
import com.devrachit.swipeassignment.data.models.ProductItem
import com.devrachit.swipeassignment.domain.repository.local.LocalProductsRepository
import kotlinx.coroutines.flow.Flow


class LocalProductsRepositoryImpl (
    private val productItemDao: ProductItemDao
): LocalProductsRepository {
    override suspend fun upsertProductEntity(entity: ProductItem) {
        productItemDao.upsertProduct(entity)
    }
    override suspend fun upsertProductList(entities: List<ProductItem>) {
        productItemDao.upsertProductList(entities)
    }
    override suspend fun deleteProduct(entity: ProductItem) {
        productItemDao.deleteProduct(entity)
    }
    override suspend fun fetchProductById(id: Int): Flow<ProductItem> {
        return productItemDao.fetchProductById(id)
    }
    override suspend fun fetchAllProducts(): Flow<List<ProductItem>> {
        return productItemDao.fetchAllProducts()
    }

    override suspend fun isTableEmpty(): Flow<Int> {
        return productItemDao.isTableEmpty()
    }

    override suspend fun clearTable() {
        productItemDao.clearTable()
    }

    override suspend fun fetchProductsByType(productType: String): Flow<List<ProductItem>> {
        return productItemDao.fetchProductsByType(productType)
    }

    override suspend fun fetchProductsByName(productName: String): Flow<List<ProductItem>> {
        return productItemDao.fetchProductsByName(productName)
    }

    override suspend fun fetchProductsByPrice(price: Double): Flow<List<ProductItem>> {
        return productItemDao.fetchProductsByPrice(price)
    }

    override suspend fun fetchProductsByTax(tax: Double): Flow<List<ProductItem>> {
        return productItemDao.fetchProductsByTax(tax)
    }

    override suspend fun fetchProductsByNameAndType(
        productName: String,
        productType: String
    ): Flow<List<ProductItem>> {
        return productItemDao.fetchProductsByNameAndType(productName, productType)
    }

    override suspend fun fetchProductsByNameAndPrice(
        productName: String,
        price: Double
    ): Flow<List<ProductItem>> {
        return productItemDao.fetchProductsByNameAndPrice(productName, price)
    }

    override suspend fun fetchProductsByNameAndTax(
        productName: String,
        tax: Double
    ): Flow<List<ProductItem>> {
        return productItemDao.fetchProductsByNameAndTax(productName, tax)
    }

    override suspend fun fetchProductsByTypeAndPrice(
        productType: String,
        price: Double
    ): Flow<List<ProductItem>> {
        return productItemDao.fetchProductsByTypeAndPrice(productType, price)
    }

    override suspend fun fetchProductsByTypeAndTax(
        productType: String,
        tax: Double
    ): Flow<List<ProductItem>> {
        return productItemDao.fetchProductsByTypeAndTax(productType, tax)
    }

    override suspend fun fetchProductsByPriceAndTax(
        price: Double,
        tax: Double
    ): Flow<List<ProductItem>> {
        return productItemDao.fetchProductsByPriceAndTax(price, tax)
    }
}
