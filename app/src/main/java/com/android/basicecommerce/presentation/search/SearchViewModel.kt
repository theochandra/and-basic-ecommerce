package com.android.basicecommerce.presentation.search

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.basicecommerce.presentation.mapper.DataMapperVM
import com.android.basicecommerce.presentation.model.ProductVM
import com.android.domain.Result
import com.android.domain.usecase.GetSearchedDataUseCase
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getSearchedDataUseCase: GetSearchedDataUseCase,
    private val mapper: DataMapperVM
) : ViewModel() {

    private val _productList = MutableLiveData<List<ProductVM>>()
    val productList: LiveData<List<ProductVM>>
        get() = _productList

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _exception = MutableLiveData<Exception>()
    val exception: LiveData<Exception>
        get() = _exception

    val input = MutableLiveData<String>()
    fun getInput(): LiveData<String> {
        return input
    }

    val isLoading = ObservableBoolean()

    /**
     * it suppose to receive keywords parameter
     * and return data based on keywords
     * because we just use static data, we don't use keywords parameter
     */
    fun getDataByQuery() {
        changeLoadingState(true)
        viewModelScope.launch {
            when (val result = getSearchedDataUseCase.execute()) {
                is Result.Success -> {
                    _productList.postValue(result.data.map {
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

    fun changeLoadingState(state: Boolean) {
        isLoading.set(state)
    }

}