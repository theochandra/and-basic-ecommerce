package com.android.domain.repository

import com.android.domain.Result
import com.android.domain.model.Data
import com.android.domain.model.ProductPromo

interface BasicEcommerceRepository {

    suspend fun login(userName: String, password: String): Boolean

    suspend fun getHomeScreenData(): Result<Data>

    suspend fun getSearchedData(): Result<List<ProductPromo>>

    suspend fun getPurchasedProducts(): Result<List<ProductPromo>>

    suspend fun addPurchasedProduct(product: ProductPromo): Long

    suspend fun removePurchasedProduct(product: ProductPromo): Int

}