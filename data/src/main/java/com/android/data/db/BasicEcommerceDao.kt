package com.android.data.db

import androidx.room.*
import com.android.data.entity.Product

@Dao
interface BasicEcommerceDao {

    @Query("SELECT * FROM TABLE_PRODUCT")
    suspend fun getPurchasedProducts(): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPurchasedProduct(product: Product): Long

    @Delete
    suspend fun removePurchasedProduct(product: Product): Int

}