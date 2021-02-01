package com.android.basicecommerce.presentation.product

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.android.basicecommerce.R
import com.android.basicecommerce.base.BaseActivity
import com.android.basicecommerce.databinding.ActivityProductBinding
import com.android.basicecommerce.di.Injector
import com.android.basicecommerce.presentation.model.ProductVM
import javax.inject.Inject

class ProductActivity : BaseActivity() {

    companion object {
        private const val EXTRA_PRODUCT_PARCEL = "PRODUCT_PARCEL"

        @JvmStatic
        fun newIntent(context: Context, product: ProductVM): Intent {
            val intent = Intent(context, ProductActivity::class.java)
            intent.putExtra(EXTRA_PRODUCT_PARCEL, product)
            return intent
        }

    }

    @Inject
    lateinit var factory: ProductViewModelFactory

    private lateinit var binding: ActivityProductBinding
    private lateinit var viewModel: ProductViewModel

    private var isLoved = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_product)

        (application as Injector).createProductSubComponent()
            .inject(this)

        viewModel = ViewModelProvider(this, factory)
            .get(ProductViewModel::class.java)

        val product = intent.getParcelableExtra<ProductVM>(EXTRA_PRODUCT_PARCEL)
        binding.product = product
        binding.viewModel = viewModel

        isLoved = product!!.loved

        handleArrowBackClicked()
        handleFavProductClicked()
        handleShareClicked(product.title)
        handlePurchaseClicked(product)
    }

    private fun handleArrowBackClicked() {
        binding.ivBack.setOnClickListener {
            onBackPressed()
        }
    }

    /**
     * this method just change the appearance of icon favorite
     * there is no API call nor save on local database
     * so there is no need to handle it on viewModel
     */
    private fun handleFavProductClicked() {
        binding.ivFavorite.setOnClickListener {
            isLoved = if (isLoved == 1) {
                binding.ivFavorite.setImageResource(R.drawable.ic_action_favorite)
                0
            } else {
                binding.ivFavorite.setImageResource(R.drawable.ic_action_favorite_filled)
                1
            }
        }
    }

    private fun handleShareClicked(productTitle: String) {
        binding.ivShare.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            val shareBody = getString(R.string.label_share_body, productTitle)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.label_share_subject));
            intent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(intent, getString(R.string.label_share_using)))
        }
    }

    private fun handlePurchaseClicked(product: ProductVM) {
        binding.btnPurchase.setOnClickListener {
            viewModel.addPurchasedProduct(product).observe(this, { newPurchasedProductId ->
                if (newPurchasedProductId != null) {
                    // success add new purchased product
                    showAlertMessage(
                        getString(R.string.label_alert_title),
                        getString(R.string.label_alert_success_add_purchased_product, product.title),
                        getString(R.string.label_alert_neutral_button)
                    )
                } else {
                    // failed add new purchased product
                    showAlertMessage(
                        getString(R.string.label_alert_title),
                        getString(R.string.label_alert_failed_add_purchased_product, product.title),
                        getString(R.string.label_alert_neutral_button)
                    )
                }
            })
        }
    }

}