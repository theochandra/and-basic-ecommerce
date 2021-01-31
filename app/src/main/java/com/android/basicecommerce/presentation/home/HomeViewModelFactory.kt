package com.android.basicecommerce.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.basicecommerce.presentation.mapper.DataMapperVM
import com.android.domain.usecase.GetHomeScreenDataUseCase

class HomeViewModelFactory(
    private val getHomeScreenDataUseCase: GetHomeScreenDataUseCase,
    private val dataMapperVM: DataMapperVM
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(getHomeScreenDataUseCase, dataMapperVM) as T
    }

}