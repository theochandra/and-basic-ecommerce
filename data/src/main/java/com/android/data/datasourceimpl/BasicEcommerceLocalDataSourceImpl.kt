package com.android.data.datasourceimpl

import com.android.data.db.BasicEcommerceDao
import com.android.data.entity.Product
import com.android.data.datasource.BasicEcommerceLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class BasicEcommerceLocalDataSourceImpl(
    private val dao: BasicEcommerceDao
) : BasicEcommerceLocalDataSource {

    override suspend fun getPurchasedProductsFromDb(): List<Product> {
        return dao.getPurchasedProducts()
    }

    override suspend fun addPurchasedProductFromDb(product: Product): Long {
        val rowInserted = CoroutineScope(Dispatchers.IO).async {
            dao.addPurchasedProduct(product)
        }
        return rowInserted.await()
    }

    override suspend fun removePurchasedProductFromDb(product: Product): Int {
        val rowDeleted = CoroutineScope(Dispatchers.IO).async {
            dao.removePurchasedProduct(product)
        }
        return rowDeleted.await()
    }

}