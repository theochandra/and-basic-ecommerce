package com.android.basicecommerce.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.basicecommerce.presentation.mapper.DataMapperVM
import com.android.domain.usecase.GetSearchedDataUseCase

class SearchViewModelFactory(
    private val getSearchedDataUseCase: GetSearchedDataUseCase,
    private val mapper: DataMapperVM
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchViewModel(getSearchedDataUseCase, mapper) as T
    }

}