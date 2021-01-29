package com.android.data.datasource

import com.android.data.entity.Product

interface BasicEcommerceLocalDataSource {

    suspend fun getPurchasedProductsFromDb(): List<Product>

    suspend fun addPurchasedProductFromDb(product: Product): Long

    suspend fun removePurchasedProductFromDb(product: Product): Int

}