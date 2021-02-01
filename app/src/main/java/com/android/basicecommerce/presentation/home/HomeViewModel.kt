package com.android.basicecommerce.presentation.home

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.basicecommerce.presentation.mapper.DataMapperVM
import com.android.basicecommerce.presentation.model.CategoryVM
import com.android.basicecommerce.presentation.model.ProductVM
import com.android.domain.Result
import com.android.domain.usecase.GetHomeScreenDataUseCase
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getHomeScreenDataUseCase: GetHomeScreenDataUseCase,
    private val mapper: DataMapperVM
) : ViewModel() {

    private val _categoryList = MutableLiveData<List<CategoryVM>>()
    val categoryList: LiveData<List<CategoryVM>>
        get() = _categoryList

    private val _productList = MutableLiveData<List<ProductVM>>()
    val productList: LiveData<List<ProductVM>>
        get() = _productList

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _exception = MutableLiveData<Exception>()
    val exception: LiveData<Exception>
        get() = _exception

    val isLoading = ObservableBoolean()

    init {
        getData()
    }

    private fun getData() {
        changeLoadingState(true)
        viewModelScope.launch {
            when(val result = getHomeScreenDataUseCase.execute()) {
                is Result.Success -> {
                    _categoryList.postValue(result.data.categoryList.map {
                        mapper.map(it)
                    })
                    _productList.postValue(result.data.productPromoList.map {
                        mapper.map(it)
                    })
                }
                is Result.Error -> {
                    _error.postValue(result.errorMessage)
                }
                is Result.Exception -> {
                    _exception.postValue(result.exception)
                }
            }
            changeLoadingState(false)
        }
    }

    private fun changeLoadingState(state: Boolean) {
        isLoading.set(state)
    }

}