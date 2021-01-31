package com.android.basicecommerce.di.application

import com.android.data.datasource.BasicEcommerceCacheDataSource
import com.android.data.datasource.BasicEcommerceLocalDataSource
import com.android.data.datasource.BasicEcommerceRemoteDataSource
import com.android.data.mapper.DataMapper
import com.android.data.repository.BasicEcommerceRepositoryImpl
import com.android.domain.repository.BasicEcommerceRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideBasicEcommerceRepository(
        cacheDataSource: BasicEcommerceCacheDataSource,
        localDataSource: BasicEcommerceLocalDataSource,
        remoteDataSource: BasicEcommerceRemoteDataSource,
        mapper: DataMapper
    ): BasicEcommerceRepository {
        return BasicEcommerceRepositoryImpl(
            cacheDataSource,
            localDataSource,
            remoteDataSource,
            mapper
        )
    }

}