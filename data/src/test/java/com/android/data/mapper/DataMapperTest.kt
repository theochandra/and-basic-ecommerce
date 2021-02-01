package com.android.data.mapper

import com.android.data.response.CategoryResponse
import com.android.data.response.DataResponse
import com.android.data.response.DataResultResponse
import com.android.data.response.ProductPromoResponse
import com.android.domain.model.ProductPromo
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DataMapperTest {

    private lateinit var sut: DataMapper

    private lateinit var dataResponseList: List<DataResultResponse>
    private lateinit var dataResultResponse: DataResultResponse
    private lateinit var dataResponse: DataResponse
    private lateinit var categoryResponse: CategoryResponse
    private lateinit var productPromoResponse: ProductPromoResponse

    private lateinit var productModelList: List<com.android.data.model.Product>
    private lateinit var modelProduct: com.android.data.model.Product

    private lateinit var productEntityList: List<com.android.data.entity.Product>
    private lateinit var entityProduct: com.android.data.entity.Product

    private lateinit var productPromo: ProductPromo

    @Before
    fun setup() {
        sut = DataMapper()

        productPromoResponse = ProductPromoResponse(
            id = 113,
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Nintendo-ds-lite.svg/430px-Nintendo-ds-lite.svg.png",
            title = "nunc mattis",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem donec massa sapien faucibus et molestie. Ut tellus elementum sagittis vitae et leo duis ut diam. Rutrum quisque non tellus orci ac auctor augue. Suspendisse potenti nullam ac tortor vitae purus faucibus ornare suspendisse. Ut sem viverra aliquet eget sit amet tellus cras adipiscing. Sit amet mauris commodo quis imperdiet massa tincidunt nunc pulvinar. Elit eget gravida cum sociis natoque. Nulla porttitor massa id neque aliquam. Malesuada fames ac turpis egestas. Nunc congue nisi vitae suscipit tellus. Enim lobortis scelerisque fermentum dui faucibus in. Lacus luctus accumsan tortor posuere ac ut consequat semper viverra.",
            price = "$330",
            loved = 1
        )
        categoryResponse = CategoryResponse(
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Nintendo-ds-lite.svg/430px-Nintendo-ds-lite.svg.png",
            id = 113,
            name = "nunc mattis"
        )
        dataResponse = DataResponse(
            listOf(categoryResponse),
            listOf(productPromoResponse)
        )
        dataResultResponse = DataResultResponse(dataResponse)
        dataResponseList = listOf(dataResultResponse)

        modelProduct = com.android.data.model.Product(
            id = 113,
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Nintendo-ds-lite.svg/430px-Nintendo-ds-lite.svg.png",
            title = "nunc mattis",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem donec massa sapien faucibus et molestie. Ut tellus elementum sagittis vitae et leo duis ut diam. Rutrum quisque non tellus orci ac auctor augue. Suspendisse potenti nullam ac tortor vitae purus faucibus ornare suspendisse. Ut sem viverra aliquet eget sit amet tellus cras adipiscing. Sit amet mauris commodo quis imperdiet massa tincidunt nunc pulvinar. Elit eget gravida cum sociis natoque. Nulla porttitor massa id neque aliquam. Malesuada fames ac turpis egestas. Nunc congue nisi vitae suscipit tellus. Enim lobortis scelerisque fermentum dui faucibus in. Lacus luctus accumsan tortor posuere ac ut consequat semper viverra.",
            price = "$330",
            loved = 1
        )
        productModelList = listOf(modelProduct)

        entityProduct = com.android.data.entity.Product(
            id = 113,
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Nintendo-ds-lite.svg/430px-Nintendo-ds-lite.svg.png",
            title = "nunc mattis",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem donec massa sapien faucibus et molestie. Ut tellus elementum sagittis vitae et leo duis ut diam. Rutrum quisque non tellus orci ac auctor augue. Suspendisse potenti nullam ac tortor vitae purus faucibus ornare suspendisse. Ut sem viverra aliquet eget sit amet tellus cras adipiscing. Sit amet mauris commodo quis imperdiet massa tincidunt nunc pulvinar. Elit eget gravida cum sociis natoque. Nulla porttitor massa id neque aliquam. Malesuada fames ac turpis egestas. Nunc congue nisi vitae suscipit tellus. Enim lobortis scelerisque fermentum dui faucibus in. Lacus luctus accumsan tortor posuere ac ut consequat semper viverra.",
            price = "$330",
            loved = 1
        )
        productEntityList = listOf(entityProduct)

        productPromo = ProductPromo(
            id = 113,
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Nintendo-ds-lite.svg/430px-Nintendo-ds-lite.svg.png",
            title = "nunc mattis",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem donec massa sapien faucibus et molestie. Ut tellus elementum sagittis vitae et leo duis ut diam. Rutrum quisque non tellus orci ac auctor augue. Suspendisse potenti nullam ac tortor vitae purus faucibus ornare suspendisse. Ut sem viverra aliquet eget sit amet tellus cras adipiscing. Sit amet mauris commodo quis imperdiet massa tincidunt nunc pulvinar. Elit eget gravida cum sociis natoque. Nulla porttitor massa id neque aliquam. Malesuada fames ac turpis egestas. Nunc congue nisi vitae suscipit tellus. Enim lobortis scelerisque fermentum dui faucibus in. Lacus luctus accumsan tortor posuere ac ut consequat semper viverra.",
            price = "$330",
            loved = 1
        )
    }

    @Test
    fun `maps list data result response into data category list`() {
        val result = sut.map(dataResponseList)
        Assert.assertEquals(result.categoryList,
            dataResponseList[0].dataResponse.categoryResponseList.map { sut.map(it) })
    }

    @Test
    fun `maps list data result response into data product promo list`() {
        val result = sut.map(dataResponseList)
        Assert.assertEquals(result.productPromoList,
            dataResponseList[0].dataResponse.productPromoResponseList.map { sut.map(it) })
    }

    @Test
    fun `maps category response into category image url`() {
        val result = sut.map(categoryResponse)
        Assert.assertEquals(result.imageUrl, categoryResponse.imageUrl)
    }

    @Test
    fun `maps category response into category id`() {
        val result = sut.map(categoryResponse)
        Assert.assertEquals(result.id, categoryResponse.id)
    }

    @Test
    fun `maps category response into category name`() {
        val result = sut.map(categoryResponse)
        Assert.assertEquals(result.name, categoryResponse.name)
    }

    @Test
    fun `maps product promo response into product promo id`() {
        val result = sut.map(productPromoResponse)
        Assert.assertEquals(result.id, productPromoResponse.id)
    }

    @Test
    fun `maps product promo response into product promo image url`() {
        val result = sut.map(productPromoResponse)
        Assert.assertEquals(result.imageUrl, productPromoResponse.imageUrl)
    }

    @Test
    fun `maps product promo response into product promo title`() {
        val result = sut.map(productPromoResponse)
        Assert.assertEquals(result.title, productPromoResponse.title)
    }

    @Test
    fun `maps product promo response into product promo description`() {
        val result = sut.map(productPromoResponse)
        Assert.assertEquals(result.description, productPromoResponse.description)
    }

    @Test
    fun `maps product promo response into product promo price`() {
        val result = sut.map(productPromoResponse)
        Assert.assertEquals(result.price, productPromoResponse.price)
    }

    @Test
    fun `maps product promo response into product promo loved`() {
        val result = sut.map(productPromoResponse)
        Assert.assertEquals(result.loved, productPromoResponse.loved)
    }

    @Test
    fun `maps list model product into list product promo`() {
        val result = sut.map(productModelList)
        Assert.assertEquals(result, productModelList.map { sut.map(it) })
    }

    @Test
    fun `maps model product into product promo id`() {
        val result = sut.map(modelProduct)
        Assert.assertEquals(result.id, modelProduct.id)
    }

    @Test
    fun `maps model product into product promo image url`() {
        val result = sut.map(modelProduct)
        Assert.assertEquals(result.imageUrl, modelProduct.imageUrl)
    }

    @Test
    fun `maps model product into product promo title`() {
        val result = sut.map(modelProduct)
        Assert.assertEquals(result.title, modelProduct.title)
    }

    @Test
    fun `maps model product into product promo description`() {
        val result = sut.map(modelProduct)
        Assert.assertEquals(result.description, modelProduct.description)
    }

    @Test
    fun `maps model product into product promo price`() {
        val result = sut.map(modelProduct)
        Assert.assertEquals(result.price, modelProduct.price)
    }

    @Test
    fun `maps model product into product promo loved`() {
        val result = sut.map(modelProduct)
        Assert.assertEquals(result.loved, modelProduct.loved)
    }

    @Test
    fun `maps list entity product into list product promo`() {
        val result = sut.mapProductEntity(productEntityList)
        Assert.assertEquals(result, productEntityList.map { sut.map(it) })
    }

    @Test
    fun `maps entity product into product promo id`() {
        val  result = sut.map(entityProduct)
        Assert.assertEquals(result.id, entityProduct.id)
    }

    @Test
    fun `maps entity product into product promo image url`() {
        val  result = sut.map(entityProduct)
        Assert.assertEquals(result.imageUrl, entityProduct.imageUrl)
    }

    @Test
    fun `maps entity product into product promo title`() {
        val  result = sut.map(entityProduct)
        Assert.assertEquals(result.title, entityProduct.title)
    }

    @Test
    fun `maps entity product into product promo description`() {
        val  result = sut.map(entityProduct)
        Assert.assertEquals(result.description, entityProduct.description)
    }

    @Test
    fun `maps entity product into product promo price`() {
        val  result = sut.map(entityProduct)
        Assert.assertEquals(result.price, entityProduct.price)
    }

    @Test
    fun `maps entity product into product promo loved`() {
        val  result = sut.map(entityProduct)
        Assert.assertEquals(result.loved, entityProduct.loved)
    }

    @Test
    fun `maps product promo into entity product id`() {
        val result = sut.map(productPromo)
        Assert.assertEquals(result.id, productPromo.id)
    }

    @Test
    fun `maps product promo into entity product image url`() {
        val result = sut.map(productPromo)
        Assert.assertEquals(result.imageUrl, productPromo.imageUrl)
    }

    @Test
    fun `maps product promo into entity product title`() {
        val result = sut.map(productPromo)
        Assert.assertEquals(result.title, productPromo.title)
    }

    @Test
    fun `maps product promo into entity product description`() {
        val result = sut.map(productPromo)
        Assert.assertEquals(result.description, productPromo.description)
    }

    @Test
    fun `maps product promo into entity product price`() {
        val result = sut.map(productPromo)
        Assert.assertEquals(result.price, productPromo.price)
    }

    @Test
    fun `maps product promo into entity product loved`() {
        val result = sut.map(productPromo)
        Assert.assertEquals(result.loved, productPromo.loved)
    }
    
}