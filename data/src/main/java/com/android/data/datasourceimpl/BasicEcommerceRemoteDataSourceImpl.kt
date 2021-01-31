package com.android.data.datasourceimpl

import com.android.data.api.BasicEcommerceService
import com.android.data.datasource.BasicEcommerceRemoteDataSource
import com.android.data.response.DataResultResponse
import retrofit2.Response

class BasicEcommerceRemoteDataSourceImpl(
    private val service: BasicEcommerceService
) : BasicEcommerceRemoteDataSource {

    override suspend fun getHomeScreenData(): Response<List<DataResultResponse>> =
        service.getHomeScreenData()

}