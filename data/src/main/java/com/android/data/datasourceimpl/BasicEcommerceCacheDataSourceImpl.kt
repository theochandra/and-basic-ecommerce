package com.android.data.datasourceimpl

import com.android.data.model.Product
import com.android.data.datasource.BasicEcommerceCacheDataSource
import kotlinx.coroutines.delay

class BasicEcommerceCacheDataSourceImpl : BasicEcommerceCacheDataSource {

    override suspend fun loginFromCache(userName: String, password: String): Boolean {
        delay(1000)
        return true
    }

    override suspend fun getSearchedDataFromCache(): List<Product> {
        val productList = ArrayList<Product>()
        productList.add(Product(
            id = 111,
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/7/76/Nintendo-Switch-Console-Docked-wJoyConRB.jpg/430px-Nintendo-Switch-Console-Docked-wJoyConRB.jpg",
            title = "Lorem ipsum dolor",
            description = "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit",
            price = "$230",
            loved = 1
        ))
        productList.add(Product(
            id = 112,
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/NES-Console-Set.jpg/430px-NES-Console-Set.jpg",
            title = "Lorem ipsum dolor sit",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            price = "$140",
            loved = 0
        ))
        productList.add(Product(
            id = 113,
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Nintendo-ds-lite.svg/430px-Nintendo-ds-lite.svg.png",
            title = "nunc mattis",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Lorem donec massa sapien faucibus et molestie. Ut tellus elementum sagittis vitae et leo duis ut diam. Rutrum quisque non tellus orci ac auctor augue. Suspendisse potenti nullam ac tortor vitae purus faucibus ornare suspendisse. Ut sem viverra aliquet eget sit amet tellus cras adipiscing. Sit amet mauris commodo quis imperdiet massa tincidunt nunc pulvinar. Elit eget gravida cum sociis natoque. Nulla porttitor massa id neque aliquam. Malesuada fames ac turpis egestas. Nunc congue nisi vitae suscipit tellus. Enim lobortis scelerisque fermentum dui faucibus in. Lacus luctus accumsan tortor posuere ac ut consequat semper viverra.",
            price = "$330",
            loved = 1
        ))
        productList.add(Product(
            id = 114,
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/1/14/Wii-console.jpg/430px-Wii-console.jpg",
            title = "morbi tristique senectus",
            description = "Egestas sed tempus urna et pharetra. Aliquam ut porttitor leo a diam sollicitudin. Amet nulla facilisi morbi tempus. Nulla malesuada pellentesque elit eget gravida cum sociis natoque. Ante metus dictum at tempor commodo ullamcorper. Volutpat sed cras ornare arcu dui vivamus arcu felis. Et magnis dis parturient montes nascetur ridiculus mus mauris. Lectus vestibulum mattis ullamcorper velit sed ullamcorper morbi tincidunt ornare. Massa sed elementum tempus egestas sed sed. Adipiscing vitae proin sagittis nisl. Sem viverra aliquet eget sit amet tellus cras. Gravida cum sociis natoque penatibus et magnis dis parturient montes. Velit aliquet sagittis id consectetur purus ut.",
            price = "$760",
            loved = 1
        ))
        productList.add(Product(
            id = 115,
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d5/Nintendo-ds-lite.svg/430px-Nintendo-ds-lite.svg.png",
            title = "ultrices tincidunt",
            description = "Tristique senectus et netus et. Nibh ipsum consequat nisl vel pretium lectus quam id leo. Mi proin sed libero enim sed faucibus turpis in eu. Ultricies mi quis hendrerit dolor magna eget. Egestas maecenas pharetra convallis posuere. Faucibus et molestie ac feugiat sed lectus vestibulum. Lacus viverra vitae congue eu consequat ac felis. Tristique senectus et netus et malesuada fames ac turpis. Tincidunt tortor aliquam nulla facilisi cras fermentum odio eu feugiat. Adipiscing bibendum est ultricies integer quis auctor elit sed. Varius duis at consectetur lorem donec massa sapien faucibus. Posuere urna nec tincidunt praesent semper feugiat. Porta non pulvinar neque laoreet. Morbi tincidunt ornare massa eget egestas. Aliquam id diam maecenas ultricies mi eget mauris. Eleifend donec pretium vulputate sapien nec sagittis aliquam malesuada bibendum. Fames ac turpis egestas integer eget aliquet nibh praesent.",
            price = "$599",
            loved = 0
        ))

        delay(1500)
        return productList
    }

}