package com.android.basicecommerce.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.basicecommerce.R
import com.android.basicecommerce.base.BaseFragment
import com.android.basicecommerce.databinding.FragmentProfileBinding
import com.android.basicecommerce.di.Injector
import com.android.basicecommerce.presentation.model.ProductVM
import com.android.basicecommerce.presentation.product.ProductActivity
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

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, factory)
            .get(ProfileViewModel::class.java)

        initRecyclerView()

        observeProductList()
        observeException()
        observeError()
        observeRowDeleted()

        handleBackClicked()
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

    private fun productItemClicked(product: ProductVM) {
        val intent = activity?.let { ProductActivity.newIntent(it, product) }
        startActivity(intent)
    }

    private fun removeProductClicked(product: ProductVM) {
        viewModel.removePurchasedProduct(product)
    }

    private fun observeProductList() {
        viewModel.productList.observe(viewLifecycleOwner, { products ->
            productAdapter.setProductList(products)
        })
    }

    private fun observeException() {
        viewModel.exception.observe(viewLifecycleOwner, { exception ->
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
        viewModel.error.observe(viewLifecycleOwner, { errorMessage ->
            showAlertMessage(
                getString(R.string.label_alert_title),
                errorMessage,
                getString(R.string.label_alert_neutral_button)
            )
        })
    }

    private fun observeRowDeleted() {
        viewModel.rowDeleted.observe(viewLifecycleOwner, { rowDeleted ->
            if (rowDeleted > 0) {
                showAlertMessage(
                    getString(R.string.label_alert_title),
                    getString(R.string.label_alert_success_remove_purchased_product),
                    getString(R.string.label_alert_neutral_button)
                )
            }
        })
    }

    private fun handleBackClicked() {
        binding.ivBack.setOnClickListener {
            it.findNavController().popBackStack()
        }
    }

}