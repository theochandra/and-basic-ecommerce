package com.android.data.response

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("category") val categoryResponseList: List<CategoryResponse>,
    @SerializedName("productPromo") val productPromoResponseList: List<ProductPromoResponse>
)