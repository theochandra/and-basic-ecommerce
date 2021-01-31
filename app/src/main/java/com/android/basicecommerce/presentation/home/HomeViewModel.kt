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

    val isLoading = ObservableBoolean()

    init {
        getData()
    }

    private fun getData() {
        changeLoadingState(true)
        viewModelScope.launch {
            val result = getHomeScreenDataUseCase.execute()
            when(result) {
                is Result.Success -> {
                    _categoryList.postValue(result.data.categoryList.map {
                        mapper.map(it)
                    })
                    _productList.postValue(result.data.productPromoList.map {
                        mapper.map(it)
                    })
                }
                is Result.Error -> {

                }
                is Result.Exception -> {

                }
            }
            changeLoadingState(false)
        }
    }

    fun changeLoadingState(state: Boolean) {
        isLoading.set(state)
    }

}