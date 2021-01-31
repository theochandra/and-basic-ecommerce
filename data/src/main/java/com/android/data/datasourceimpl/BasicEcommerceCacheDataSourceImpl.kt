package com.android.data.datasourceimpl

import com.android.data.model.Product
import com.android.data.datasource.BasicEcommerceCacheDataSource
import kotlinx.coroutines.delay

class BasicEcommerceCacheDataSourceImpl : BasicEcommerceCacheDataSource {

    private var productList = ArrayList<Product>()

    override suspend fun loginFromCache(userName: String, password: String): Boolean {
        delay(1000)
        return true
    }

    override suspend fun getSearchedDataFromCache(): List<Product> {
        return productList
    }

}