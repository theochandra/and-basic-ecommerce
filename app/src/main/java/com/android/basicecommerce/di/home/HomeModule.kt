package com.android.basicecommerce.di.home

import com.android.basicecommerce.presentation.home.HomeViewModelFactory
import com.android.basicecommerce.presentation.mapper.DataMapperVM
import com.android.domain.usecase.GetHomeScreenDataUseCase
import dagger.Module
import dagger.Provides

@Module
class HomeModule {

    @HomeScope
    @Provides
    fun provideHomeViewModelFactory(
        getHomeScreenDataUseCase: GetHomeScreenDataUseCase,
        dataMapperVM: DataMapperVM
    ): HomeViewModelFactory {
        return HomeViewModelFactory(getHomeScreenDataUseCase, dataMapperVM)
    }

}