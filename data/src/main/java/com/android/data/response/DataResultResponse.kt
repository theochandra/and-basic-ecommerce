package com.android.data.response

import com.google.gson.annotations.SerializedName

data class DataResultResponse(
    @SerializedName("data") val dataResponse: DataResponse
)
