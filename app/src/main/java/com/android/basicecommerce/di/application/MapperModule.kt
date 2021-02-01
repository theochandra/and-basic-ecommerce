package com.android.basicecommerce.di.application

import com.android.basicecommerce.presentation.mapper.DataMapperVM
import com.android.basicecommerce.presentation.mapper.ProductMapperVM
import com.android.data.mapper.DataMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapperModule {

    @Singleton
    @Provides
    fun provideDataMapper(): DataMapper {
        return DataMapper()
    }

    @Singleton
    @Provides
    fun provideDataMapperVM(): DataMapperVM {
        return DataMapperVM()
    }

    @Singleton
    @Provides
    fun provideProductMapperVM(): ProductMapperVM {
        return ProductMapperVM()
    }

}