package com.android.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.data.entity.Product

@Database(
    entities = [Product::class],
    version = 1,
    exportSchema = false
)
abstract class BasicEcommerceDatabase : RoomDatabase() {

    abstract fun basicEcommerceDao(): BasicEcommerceDao

}