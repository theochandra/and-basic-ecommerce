package com.android.basicecommerce.presentation.model

data class ProductVM(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val description: String,
    val price: String,
    val loved: Int
)
