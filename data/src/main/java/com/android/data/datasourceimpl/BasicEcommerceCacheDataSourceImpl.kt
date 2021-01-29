package com.android.data.datasourceimpl

import com.android.data.model.Product
import com.android.data.datasource.BasicEcommerceCacheDataSource

class BasicEcommerceCacheDataSourceImpl : BasicEcommerceCacheDataSource {

    private var productList = ArrayList<Product>()

    override suspend fun getSearchedDataFromCache(): List<Product> {
        return productList
    }

}