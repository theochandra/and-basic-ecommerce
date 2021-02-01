package com.android.basicecommerce.presentation.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.basicecommerce.databinding.ItemProductListBinding
import com.android.basicecommerce.presentation.model.ProductVM

class ProductListAdapter(
    private val itemClickListener : (ProductVM) -> Unit,
    private val removeClickListener : (ProductVM) -> Unit
) : RecyclerView.Adapter<ProductListAdapter.ProductListViewHolder>() {

    private val productList = ArrayList<ProductVM>()

    fun clearList() {
        productList.clear()
        notifyDataSetChanged()
    }

    fun setProductList(products: List<ProductVM>) {
        productList.addAll(products)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        return ProductListViewHolder(
            ItemProductListBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.bind(productList[position], itemClickListener, removeClickListener)
    }

    override fun getItemCount(): Int = productList.size

    class ProductListViewHolder(
        val binding: ItemProductListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            product: ProductVM,
            itemClickListener: (ProductVM) -> Unit,
            removeClickListener: (ProductVM) -> Unit
        ) {
            binding.product = product
            binding.root.setOnClickListener {
                itemClickListener(product)
            }
            binding.ivRemove.setOnClickListener {
                removeClickListener(product)
            }
        }

    }

}