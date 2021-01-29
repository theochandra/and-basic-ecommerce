package com.android.data.repository

import com.android.data.datasource.BasicEcommerceCacheDataSource
import com.android.data.datasource.BasicEcommerceLocalDataSource
import com.android.data.datasource.BasicEcommerceRemoteDataSource
import com.android.domain.Result
import com.android.domain.model.Data
import com.android.domain.model.ProductPromo
import com.android.domain.repository.BasicEcommerceRepository

class BasicEcommerceRepositoryImpl(
    private val cacheDataSource: BasicEcommerceCacheDataSource,
    private val localDataSource: BasicEcommerceLocalDataSource,
    private val remoteDataSource: BasicEcommerceRemoteDataSource
) : BasicEcommerceRepository {

    override suspend fun getHomeScreenData(): Result<Data> {
        TODO("Not yet implemented")
    }

    override suspend fun getSearchedData(): Result<List<ProductPromo>> {
        TODO("Not yet implemented")
    }

    override suspend fun getPurchasedProducts(): Result<List<ProductPromo>> {
        TODO("Not yet implemented")
    }

    override suspend fun addPurchasedProduct(product: ProductPromo): Long {
        TODO("Not yet implemented")
    }

    override suspend fun removePurchasedProduct(product: ProductPromo): Int {
        TODO("Not yet implemented")
    }

}