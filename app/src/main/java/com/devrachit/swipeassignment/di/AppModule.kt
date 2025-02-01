package com.devrachit.swipeassignment.di

import androidx.room.Room
import com.devrachit.swipeassignment.data.local.databases.ApplicationDatabase
import com.devrachit.swipeassignment.data.remote.services.ApiServices
import com.devrachit.swipeassignment.utility.Constants.Companion.BASE_URL
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single<ApiServices> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServices::class.java)
    }
    single<ApplicationDatabase> {
        Room.databaseBuilder(
            context = androidContext(),
            ApplicationDatabase::class.java,
            "app_database"
        ).addMigrations()
            .build()
    }
}