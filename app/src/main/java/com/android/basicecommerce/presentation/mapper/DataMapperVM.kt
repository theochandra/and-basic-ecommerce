package com.android.basicecommerce.presentation.mapper

import com.android.basicecommerce.presentation.model.CategoryVM
import com.android.basicecommerce.presentation.model.ProductVM
import com.android.domain.model.Category
import com.android.domain.model.ProductPromo

class DataMapperVM {

    fun map(categoryList: List<Category>): List<CategoryVM> {
        return categoryList.map { map(it) }
    }

    fun map(category: Category): CategoryVM {
        return CategoryVM(
            imageUrl = category.imageUrl,
            id = category.id,
            name = category.name
        )
    }

    fun map(product: ProductPromo): ProductVM {
        return ProductVM(
            id = product.id,
            imageUrl = product.imageUrl,
            title = product.title,
            description = product.description,
            price = product.price,
            loved = product.loved
        )
    }

}