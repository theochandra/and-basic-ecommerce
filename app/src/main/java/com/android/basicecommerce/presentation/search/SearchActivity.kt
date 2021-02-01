package com.android.basicecommerce.presentation.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.basicecommerce.R
import com.android.basicecommerce.base.BaseActivity
import com.android.basicecommerce.databinding.ActivitySearchBinding
import com.android.basicecommerce.di.Injector
import com.android.basicecommerce.presentation.model.ProductVM
import com.android.basicecommerce.presentation.product.ProductActivity
import kotlinx.coroutines.*
import javax.inject.Inject

class SearchActivity : BaseActivity() {

    companion object {
        @JvmStatic
        fun newIntent(context: Context): Intent {
            return Intent(context, SearchActivity::class.java)
        }
    }

    @Inject
    lateinit var factory: SearchViewModelFactory

    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: SearchViewModel

    private lateinit var productAdapter: ProductAdapter

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        (application as Injector).createSearchSubComponent()
            .inject(this)

        viewModel = ViewModelProvider(this, factory)
            .get(SearchViewModel::class.java)

        binding.viewModel = viewModel

        initRecyclerView()
        observeInput()
        observeData()
        observeException()
        observeError()
        handleArrowBackClicked()
    }

    private fun initRecyclerView() {
        productAdapter = ProductAdapter {
            selectedProduct: ProductVM -> productItemClicked(selectedProduct)
        }
        binding.rvProduct.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity,
                LinearLayoutManager.VERTICAL, false)
            adapter = productAdapter
        }
    }

    private fun productItemClicked(product: ProductVM) {
        val intent = ProductActivity.newIntent(this@SearchActivity, product)
        startActivity(intent)
    }

    private fun observeInput() {
        viewModel.getInput().observe(this, { input ->
            searchJob?.cancel()
            productAdapter.clearList()
            viewModel.changeLoadingState(false)

            if (input.isNullOrEmpty()) return@observe
            if (input.length < 3) return@observe
            searchJob = coroutineScope.launch {
                delay(500)
                viewModel.getDataByQuery()
            }
        })
    }

    private fun observeData() {
        viewModel.productList.observe(this, {
            productAdapter.setProductList(it)
        })
    }

    private fun observeException() {
        viewModel.exception.observe(this, { exception ->
            exception.message?.let { message ->
                showAlertMessage(
                    getString(R.string.label_alert_title),
                    message,
                    getString(R.string.label_alert_neutral_button)
                )
            }
        })
    }

    private fun observeError() {
        viewModel.error.observe(this, { errorMessage ->
            showAlertMessage(
                getString(R.string.label_alert_title),
                errorMessage,
                getString(R.string.label_alert_neutral_button)
            )
        })
    }

    private fun handleArrowBackClicked() {
        binding.searchBar.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

}