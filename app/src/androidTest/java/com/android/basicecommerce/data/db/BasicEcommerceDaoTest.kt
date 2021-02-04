package com.android.basicecommerce.data.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.android.data.db.BasicEcommerceDao
import com.android.data.db.BasicEcommerceDatabase
import com.android.data.entity.Product
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class BasicEcommerceDaoTest {

    private lateinit var database: BasicEcommerceDatabase
    private lateinit var dao: BasicEcommerceDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context, BasicEcommerceDatabase::class.java).build()
        dao = database.basicEcommerceDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun addPurchasedProduct() {
        val product = Product(
            id = 113,
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Nintendo-ds-lite.svg/430px-Nintendo-ds-lite.svg.png",
            title = "nunc mattis",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem donec massa sapien faucibus et molestie. Ut tellus elementum sagittis vitae et leo duis ut diam. Rutrum quisque non tellus orci ac auctor augue. Suspendisse potenti nullam ac tortor vitae purus faucibus ornare suspendisse. Ut sem viverra aliquet eget sit amet tellus cras adipiscing. Sit amet mauris commodo quis imperdiet massa tincidunt nunc pulvinar. Elit eget gravida cum sociis natoque. Nulla porttitor massa id neque aliquam. Malesuada fames ac turpis egestas. Nunc congue nisi vitae suscipit tellus. Enim lobortis scelerisque fermentum dui faucibus in. Lacus luctus accumsan tortor posuere ac ut consequat semper viverra.",
            price = "$330",
            loved = 1
        )
        runBlocking {
            val newID = dao.addPurchasedProduct(product)
            val result = dao.getPurchasedProducts()
            assertThat(result[0], equalTo(product))
        }
    }

    @Test
    @Throws(Exception::class)
    fun removePurchasedProductById() {
        val product = Product(
            id = 113,
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Nintendo-ds-lite.svg/430px-Nintendo-ds-lite.svg.png",
            title = "nunc mattis",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem donec massa sapien faucibus et molestie. Ut tellus elementum sagittis vitae et leo duis ut diam. Rutrum quisque non tellus orci ac auctor augue. Suspendisse potenti nullam ac tortor vitae purus faucibus ornare suspendisse. Ut sem viverra aliquet eget sit amet tellus cras adipiscing. Sit amet mauris commodo quis imperdiet massa tincidunt nunc pulvinar. Elit eget gravida cum sociis natoque. Nulla porttitor massa id neque aliquam. Malesuada fames ac turpis egestas. Nunc congue nisi vitae suscipit tellus. Enim lobortis scelerisque fermentum dui faucibus in. Lacus luctus accumsan tortor posuere ac ut consequat semper viverra.",
            price = "$330",
            loved = 1
        )
        runBlocking {
            val newlyAddedProductId = dao.addPurchasedProduct(product)
            dao.removePurchasedProductById(newlyAddedProductId.toInt())
            val result = dao.getPurchasedProducts()
            assertThat(result, equalTo(emptyList()))
        }
    }

}