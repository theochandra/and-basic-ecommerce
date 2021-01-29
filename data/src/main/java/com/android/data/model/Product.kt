package com.android.data.model

data class Product(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val description: String,
    val price: String,
    val loved: Int
)