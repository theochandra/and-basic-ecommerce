package com.android.basicecommerce.di.search

import com.android.basicecommerce.presentation.mapper.DataMapperVM
import com.android.basicecommerce.presentation.search.SearchViewModelFactory
import com.android.domain.usecase.GetSearchedDataUseCase
import dagger.Module
import dagger.Provides

@Module
class SearchModule {

    @SearchScope
    @Provides
    fun provideSearchViewModelFactory(
        getSearchedDataUseCase: GetSearchedDataUseCase,
        mapper: DataMapperVM
    ): SearchViewModelFactory {
        return SearchViewModelFactory(
                getSearchedDataUseCase, mapper)
    }

}