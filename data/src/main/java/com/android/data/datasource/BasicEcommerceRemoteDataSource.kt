package com.android.data.datasource

import com.android.data.response.DataResponse
import retrofit2.Response

interface BasicEcommerceRemoteDataSource {

    suspend fun getHomeScreenData(): Response<List<DataResponse>>

}