package com.android.data.response

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)