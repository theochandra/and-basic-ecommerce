package com.android.data.datasource

import com.android.data.model.Product

interface BasicEcommerceCacheDataSource {

    suspend fun loginFromCache(userName: String, password: String): Boolean

    suspend fun getSearchedDataFromCache(): List<Product>

}