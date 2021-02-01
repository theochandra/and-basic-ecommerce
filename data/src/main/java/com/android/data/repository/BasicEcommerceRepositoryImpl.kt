package com.android.data.repository

import com.android.data.datasource.BasicEcommerceCacheDataSource
import com.android.data.datasource.BasicEcommerceLocalDataSource
import com.android.data.datasource.BasicEcommerceRemoteDataSource
import com.android.data.mapper.DataMapper
import com.android.data.safeApiCall
import com.android.domain.Result
import com.android.domain.model.Data
import com.android.domain.model.ProductPromo
import com.android.domain.repository.BasicEcommerceRepository

class BasicEcommerceRepositoryImpl(
    private val cacheDataSource: BasicEcommerceCacheDataSource,
    private val localDataSource: BasicEcommerceLocalDataSource,
    private val remoteDataSource: BasicEcommerceRemoteDataSource,
    private val mapper: DataMapper
) : BasicEcommerceRepository {

    override suspend fun login(userName: String, password: String): Boolean {
        return cacheDataSource.loginFromCache(userName, password)
    }

    override suspend fun getHomeScreenData(): Result<Data> {
        return safeApiCall(
            call = { getHomeScreenDataFromApi() },
            errorMessage = "Oops.. Terjadi kesalahan pada sistem!\nCobalah beberapa saat lagi."
        )
    }

    private suspend fun getHomeScreenDataFromApi(): Result<Data> {
        val result = remoteDataSource.getHomeScreenData()

        if (result.isSuccessful) {
            val body = result.body()
            if (body != null) {
                val data = mapper.map(body)
                return Result.Success(data)
            }
        }

        return Result.Error(result.code(), result.message())
    }

    override suspend fun getSearchedData(): Result<List<ProductPromo>> {
        val productList  = cacheDataSource.getSearchedDataFromCache()
        val productPromoList = mapper.map(productList)
        return Result.Success(productPromoList)
    }

    override suspend fun getPurchasedProducts(): Result<List<ProductPromo>> {
        val productList = localDataSource.getPurchasedProductsFromDb()
        val productPromoList = mapper.mapProductEntity(productList)
        return Result.Success(productPromoList)
    }

    override suspend fun addPurchasedProduct(product: ProductPromo): Long {
        val mappedProduct = mapper.map(product)
        return localDataSource.addPurchasedProductFromDb(mappedProduct)
    }

    override suspend fun removePurchasedProduct(product: ProductPromo): Int {
        val mappedProduct = mapper.map(product)
        return localDataSource.removePurchasedProductFromDb(mappedProduct)
    }

}