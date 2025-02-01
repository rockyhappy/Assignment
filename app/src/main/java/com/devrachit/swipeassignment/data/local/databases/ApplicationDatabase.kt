package com.devrachit.swipeassignment.data.local.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devrachit.swipeassignment.data.local.services.ProductItemDao
import com.devrachit.swipeassignment.data.models.ProductItem

@Database(
    entities = [ProductItem::class], version = 1
)
abstract class ApplicationDatabase : RoomDatabase() {
    abstract val productItemDao: ProductItemDao
}