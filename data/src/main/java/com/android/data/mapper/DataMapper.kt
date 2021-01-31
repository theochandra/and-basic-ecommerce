package com.android.data.mapper

import com.android.data.response.CategoryResponse
import com.android.data.response.DataResultResponse
import com.android.data.response.ProductPromoResponse
import com.android.domain.model.Category
import com.android.domain.model.Data
import com.android.domain.model.ProductPromo

class DataMapper {

    fun map(dataResponseResult: List<DataResultResponse>): Data {
        val dataResponse = dataResponseResult[0].dataResponse
        return Data(
                categoryList = dataResponse.categoryResponseList.map { map(it) },
                productPromoList = dataResponse.productPromoResponseList.map { map(it) }
        )
    }

    private fun map(categoryResponse: CategoryResponse): Category {
        return Category(
            imageUrl = categoryResponse.imageUrl,
            id = categoryResponse.id,
            name = categoryResponse.name
        )
    }

    private fun map(productPromoResponse: ProductPromoResponse): ProductPromo {
        return ProductPromo(
            id = productPromoResponse.id,
            imageUrl = productPromoResponse.imageUrl,
            title = productPromoResponse.title,
            description = productPromoResponse.description,
            price = productPromoResponse.price,
            loved = productPromoResponse.loved
        )
    }

    fun map(productList: List<com.android.data.model.Product>): List<ProductPromo> {
        return productList.map { map(it) }
    }

    private fun map(product: com.android.data.model.Product): ProductPromo {
        return ProductPromo(
            id = product.id,
            imageUrl = product.imageUrl,
            title = product.title,
            description = product.description,
            price = product.price,
            loved = product.loved
        )
    }

    fun mapProductEntity(productList: List<com.android.data.entity.Product>): List<ProductPromo> {
        return productList.map { map(it) }
    }

    private fun map(product: com.android.data.entity.Product): ProductPromo {
        return ProductPromo(
            id = product.id,
            imageUrl = product.imageUrl,
            title = product.title,
            description = product.description,
            price = product.price,
            loved = product.loved
        )
    }

    fun map(productPromo: ProductPromo): com.android.data.entity.Product {
        return com.android.data.entity.Product(
            id = productPromo.id,
            imageUrl = productPromo.imageUrl,
            title = productPromo.title,
            description = productPromo.description,
            price = productPromo.price,
            loved = productPromo.loved
        )
    }

}