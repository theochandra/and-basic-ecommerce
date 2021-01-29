package com.android.domain.model

data class ProductPromo(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val description: String,
    val price: String,
    val loved: Int
)