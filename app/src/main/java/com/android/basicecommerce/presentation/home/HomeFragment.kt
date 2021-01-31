package com.android.basicecommerce.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.basicecommerce.R
import com.android.basicecommerce.databinding.FragmentHomeBinding
import com.android.basicecommerce.di.Injector
import com.android.basicecommerce.presentation.model.ProductVM
import com.android.basicecommerce.presentation.product.ProductActivity
import javax.inject.Inject

class HomeFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    @Inject
    lateinit var factory: HomeViewModelFactory

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var productAdapter: ProductHeroAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        (activity?.application as Injector).createHomeSubComponent()
                .inject(this)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, factory)
                .get(HomeViewModel::class.java)
        binding.viewModel = viewModel

        initCategoryRecyclerView()
        initProductRecyclerView()

        observeCategoryList()
        observeProductList()
    }

    private fun initCategoryRecyclerView() {
        categoryAdapter = CategoryAdapter()
        binding.rvCategory.apply {
            layoutManager = LinearLayoutManager(activity,
                LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
        }
    }

    private fun initProductRecyclerView() {
        productAdapter = ProductHeroAdapter{
            selectedProduct: ProductVM -> productItemClicked(selectedProduct) }
        binding.rvProduct.apply {
            layoutManager = LinearLayoutManager(activity,
                LinearLayoutManager.VERTICAL, false)
            adapter = productAdapter
        }
    }

    private fun observeCategoryList() {
        viewModel.categoryList.observe(viewLifecycleOwner, { categories ->
            categoryAdapter.setCategoryList(categories)
        })
    }

    private fun observeProductList() {
        viewModel.productList.observe(viewLifecycleOwner, { products ->
            productAdapter.setProductList(products)
        })
    }

    private fun productItemClicked(product: ProductVM) {
        val intent = activity?.let { ProductActivity.newIntent(it, product) }
        startActivity(intent)
    }

}