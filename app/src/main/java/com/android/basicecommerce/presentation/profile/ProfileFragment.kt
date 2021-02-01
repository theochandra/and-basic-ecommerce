package com.android.basicecommerce.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.basicecommerce.R
import com.android.basicecommerce.base.BaseFragment
import com.android.basicecommerce.databinding.FragmentProfileBinding
import com.android.basicecommerce.di.Injector
import com.android.basicecommerce.presentation.model.ProductVM
import javax.inject.Inject

class ProfileFragment : BaseFragment() {

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }

    @Inject
    lateinit var factory: ProfileViewModelFactory

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel

    private lateinit var productAdapter: ProductListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        (activity?.application as Injector).createProfileSubComponent()
            .inject(this)

        initRecyclerView()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, factory)
            .get(ProfileViewModel::class.java)

        observeProductList()
    }

    private fun initRecyclerView() {
        productAdapter = ProductListAdapter(
            { selectedProduct: ProductVM -> productItemClicked(selectedProduct) },
            { selectedProduct: ProductVM -> removeProductClicked(selectedProduct) }
        )
        binding.rvProduct.apply {
            layoutManager = LinearLayoutManager(activity,
                LinearLayoutManager.VERTICAL, false)
            adapter = productAdapter
        }
    }

    private fun observeProductList() {
        viewModel.productList.observe(viewLifecycleOwner, { products ->
            productAdapter.setProductList(products)
        })
    }

    private fun productItemClicked(product: ProductVM) {
        Toast.makeText(activity, "item", Toast.LENGTH_SHORT).show()
    }
    
    private fun removeProductClicked(product: ProductVM) {
        Toast.makeText(activity, "remove", Toast.LENGTH_SHORT).show()
    }

}