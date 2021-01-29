package com.android.data.datasource

import com.android.data.model.Product

interface BasicEcommerceCacheDataSource {

    suspend fun getSearchedDataFromCache(): List<Product>

}