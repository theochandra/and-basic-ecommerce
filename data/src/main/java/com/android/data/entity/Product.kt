package com.android.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TABLE_PRODUCT")
data class Product(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val description: String,
    val price: String,
    val loved: Int
) {
    @PrimaryKey(autoGenerate = true)
    var idPrimaryKey: Int = 0
}