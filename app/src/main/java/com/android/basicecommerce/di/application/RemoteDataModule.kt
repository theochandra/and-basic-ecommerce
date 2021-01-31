package com.android.basicecommerce.di.application

import com.android.data.api.BasicEcommerceService
import com.android.data.datasource.BasicEcommerceRemoteDataSource
import com.android.data.datasourceimpl.BasicEcommerceRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideBasicEcommerceRemoteDataSource(
        basicEcommerceService: BasicEcommerceService
    ): BasicEcommerceRemoteDataSource {
        return BasicEcommerceRemoteDataSourceImpl(basicEcommerceService)
    }

}