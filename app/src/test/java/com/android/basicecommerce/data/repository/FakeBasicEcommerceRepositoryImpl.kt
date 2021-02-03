package com.android.basicecommerce.data.repository

import com.android.domain.Result
import com.android.domain.model.Data
import com.android.domain.model.ProductPromo
import com.android.domain.repository.BasicEcommerceRepository

class FakeBasicEcommerceRepositoryImpl : BasicEcommerceRepository {

    override suspend fun login(userName: String, password: String): Boolean {
        return true
    }

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