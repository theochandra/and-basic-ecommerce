package com.android.data.response

import com.google.gson.annotations.SerializedName

data class ProductPromoResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("price") val price: String,
    @SerializedName("loved") val loved: Int
)