package com.android.basicecommerce.di.application

import com.android.data.datasource.BasicEcommerceLocalDataSource
import com.android.data.datasourceimpl.BasicEcommerceLocalDataSourceImpl
import com.android.data.db.BasicEcommerceDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Singleton
    @Provides
    fun provideBasicEcommerceLocalDataSource(
        basicEcommerceDao: BasicEcommerceDao
    ): BasicEcommerceLocalDataSource {
        return BasicEcommerceLocalDataSourceImpl(basicEcommerceDao)
    }

}