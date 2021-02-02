package com.android.basicecommerce.presentation.mapper

import com.android.basicecommerce.presentation.model.ProductVM
import com.android.domain.model.Category
import com.android.domain.model.ProductPromo
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DataMapperVMTest {

    private lateinit var sut: DataMapperVM

    private lateinit var category: Category
    private lateinit var productPromo: ProductPromo
    private lateinit var product: ProductVM

    @Before
    fun setup() {
        sut = DataMapperVM()

        category = Category(
            imageUrl = "image url",
            id = 1,
            name = "category name"
        )

        productPromo = ProductPromo(
            id = 113,
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Nintendo-ds-lite.svg/430px-Nintendo-ds-lite.svg.png",
            title = "nunc mattis",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem donec massa sapien faucibus et molestie. Ut tellus elementum sagittis vitae et leo duis ut diam. Rutrum quisque non tellus orci ac auctor augue. Suspendisse potenti nullam ac tortor vitae purus faucibus ornare suspendisse. Ut sem viverra aliquet eget sit amet tellus cras adipiscing. Sit amet mauris commodo quis imperdiet massa tincidunt nunc pulvinar. Elit eget gravida cum sociis natoque. Nulla porttitor massa id neque aliquam. Malesuada fames ac turpis egestas. Nunc congue nisi vitae suscipit tellus. Enim lobortis scelerisque fermentum dui faucibus in. Lacus luctus accumsan tortor posuere ac ut consequat semper viverra.",
            price = "$330",
            loved = 1
        )

        product = ProductVM(
            id = 113,
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Nintendo-ds-lite.svg/430px-Nintendo-ds-lite.svg.png",
            title = "nunc mattis",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem donec massa sapien faucibus et molestie. Ut tellus elementum sagittis vitae et leo duis ut diam. Rutrum quisque non tellus orci ac auctor augue. Suspendisse potenti nullam ac tortor vitae purus faucibus ornare suspendisse. Ut sem viverra aliquet eget sit amet tellus cras adipiscing. Sit amet mauris commodo quis imperdiet massa tincidunt nunc pulvinar. Elit eget gravida cum sociis natoque. Nulla porttitor massa id neque aliquam. Malesuada fames ac turpis egestas. Nunc congue nisi vitae suscipit tellus. Enim lobortis scelerisque fermentum dui faucibus in. Lacus luctus accumsan tortor posuere ac ut consequat semper viverra.",
            price = "$330",
            loved = 1
        )
    }

    @Test
    fun `maps category into category vm image url`() {
        val result = sut.map(category)
        Assert.assertEquals(result.imageUrl, category.imageUrl)
    }

    @Test
    fun `maps category into category vm id`() {
        val result = sut.map(category)
        Assert.assertEquals(result.id, category.id)
    }

    @Test
    fun `maps category into category vm name`() {
        val result = sut.map(category)
        Assert.assertEquals(result.name, category.name)
    }

    @Test
    fun `maps product promo into product vm id`() {
        val result = sut.map(productPromo)
        Assert.assertEquals(result.id, productPromo.id)
    }

    @Test
    fun `maps product promo into product vm image url`() {
        val result = sut.map(productPromo)
        Assert.assertEquals(result.imageUrl, productPromo.imageUrl)
    }

    @Test
    fun `maps product promo into product vm title`() {
        val result = sut.map(productPromo)
        Assert.assertEquals(result.title, productPromo.title)
    }

    @Test
    fun `maps product promo into product vm description`() {
        val result = sut.map(productPromo)
        Assert.assertEquals(result.description, productPromo.description)
    }

    @Test
    fun `maps product promo into product vm price`() {
        val result = sut.map(productPromo)
        Assert.assertEquals(result.price, productPromo.price)
    }

    @Test
    fun `maps product promo into product vm loved`() {
        val result = sut.map(productPromo)
        Assert.assertEquals(result.loved, productPromo.loved)
    }

    @Test
    fun `maps product vm into product promo id`() {
        val result = sut.map(product)
        Assert.assertEquals(result.id, product.id)
    }

    @Test
    fun `maps product vm into product promo image url`() {
        val result = sut.map(product)
        Assert.assertEquals(result.imageUrl, product.imageUrl)
    }

    @Test
    fun `maps product vm into product promo title`() {
        val result = sut.map(product)
        Assert.assertEquals(result.title, product.title)
    }

    @Test
    fun `maps product vm into product promo description`() {
        val result = sut.map(product)
        Assert.assertEquals(result.description, product.description)
    }

    @Test
    fun `maps product vm into product promo price`() {
        val result = sut.map(product)
        Assert.assertEquals(result.price, product.price)
    }

    @Test
    fun `maps product vm into product promo loved`() {
        val result = sut.map(product)
        Assert.assertEquals(result.loved, product.loved)
    }

}