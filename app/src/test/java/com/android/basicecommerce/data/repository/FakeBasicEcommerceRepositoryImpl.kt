package com.android.basicecommerce.data.repository

import com.android.domain.Result
import com.android.domain.model.Data
import com.android.domain.model.ProductPromo
import com.android.domain.repository.BasicEcommerceRepository
import com.nhaarman.mockitokotlin2.mock

class FakeBasicEcommerceRepositoryImpl : BasicEcommerceRepository {

    override suspend fun login(userName: String, password: String): Boolean {
        return true
    }

    override suspend fun getHomeScreenData(): Result<Data> {
        val data: Data = mock()
        return Result.Success(data)
    }

    override suspend fun getSearchedData(): Result<List<ProductPromo>> {
        val listProductPromo = listOf<ProductPromo>()
        return Result.Success(listProductPromo)
    }

    override suspend fun getPurchasedProducts(): Result<List<ProductPromo>> {
        val listProductPromo = listOf<ProductPromo>()
        return Result.Success(listProductPromo)
    }

    override suspend fun addPurchasedProduct(product: ProductPromo): Long {
        return 1L
    }

    override suspend fun removePurchasedProduct(product: ProductPromo): Int {
        return 1
    }

}