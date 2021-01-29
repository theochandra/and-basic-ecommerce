package com.android.data.api

import com.android.data.response.DataResponse
import retrofit2.Response
import retrofit2.http.GET

interface BasicEcommerceService {

    @GET("home")
    suspend fun getHomeScreenData(): Response<List<DataResponse>>

}