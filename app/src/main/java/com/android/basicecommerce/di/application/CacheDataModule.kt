package com.android.basicecommerce.di.application

import com.android.data.datasource.BasicEcommerceCacheDataSource
import com.android.data.datasourceimpl.BasicEcommerceCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {

    @Singleton
    @Provides
    fun provideBasicEcommerceCacheDataSource(): BasicEcommerceCacheDataSource {
        return BasicEcommerceCacheDataSourceImpl()
    }

}