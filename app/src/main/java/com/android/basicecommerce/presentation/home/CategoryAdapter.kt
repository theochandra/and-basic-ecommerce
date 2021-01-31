package com.android.basicecommerce.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.basicecommerce.databinding.ItemCategoryBinding
import com.android.basicecommerce.presentation.model.CategoryVM

class CategoryAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val categoryList = ArrayList<CategoryVM>()

    fun clearList() {
        categoryList.clear()
        notifyDataSetChanged()
    }

    fun setCategoryList(categories: List<CategoryVM>) {
        categoryList.addAll(categories)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CategoryViewHolder(
            ItemCategoryBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val categoryVM = categoryList[position]
        (holder as CategoryViewHolder).binding.category = categoryVM
    }

    override fun getItemCount(): Int = categoryList.size

    class CategoryViewHolder(
        val binding: ItemCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root)

}