package com.android.basicecommerce.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.basicecommerce.databinding.ItemProductListBinding
import com.android.basicecommerce.presentation.model.ProductVM

class ProductAdapter(
    private val itemClickListener : (ProductVM) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val productList = ArrayList<ProductVM>()

    fun clearList() {
        productList.clear()
        notifyDataSetChanged()
    }

    fun setProductList(products: List<ProductVM>) {
        productList.addAll(products)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            ItemProductListBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position], itemClickListener)
    }

    override fun getItemCount(): Int = productList.size

    class ProductViewHolder(
        val binding: ItemProductListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductVM, itemClickListener: (ProductVM) -> Unit) {
            binding.product = product
            binding.root.setOnClickListener {
                itemClickListener(product)
            }
        }

    }

}