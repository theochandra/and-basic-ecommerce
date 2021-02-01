package com.android.basicecommerce.presentation.mapper

import com.android.basicecommerce.presentation.model.ProductVM
import com.android.domain.model.ProductPromo

class ProductMapperVM {

    fun map(product: ProductVM): ProductPromo {
        return ProductPromo(
            id = product.id,
            imageUrl = product.imageUrl,
            title = product.title,
            description = product.description,
            price = product.price,
            loved = product.loved
        )
    }

}