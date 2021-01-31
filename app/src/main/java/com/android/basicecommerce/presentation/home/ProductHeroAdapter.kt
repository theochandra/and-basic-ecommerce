package com.android.basicecommerce.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.basicecommerce.databinding.ItemProductHeroBinding
import com.android.basicecommerce.presentation.model.ProductVM

class ProductHeroAdapter(
    private val itemClickListener : (ProductVM) -> Unit
) : RecyclerView.Adapter<ProductHeroAdapter.ProductHeroViewHolder>() {

    private val productList = ArrayList<ProductVM>()

    fun clearList() {
        productList.clear()
        notifyDataSetChanged()
    }

    fun setProductList(products: List<ProductVM>) {
        productList.addAll(products)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHeroViewHolder {
        return ProductHeroViewHolder(
            ItemProductHeroBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductHeroViewHolder, position: Int) {
        holder.bind(productList[position], itemClickListener)
    }

    override fun getItemCount(): Int = productList.size

    class ProductHeroViewHolder(
        val binding: ItemProductHeroBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductVM, itemClickListener: (ProductVM) -> Unit) {
            binding.product = product
            binding.root.setOnClickListener {
                itemClickListener(product)
            }
        }
    }

}