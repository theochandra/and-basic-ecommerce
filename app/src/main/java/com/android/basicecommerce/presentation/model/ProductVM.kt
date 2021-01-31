package com.android.basicecommerce.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductVM(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val description: String,
    val price: String,
    val loved: Int
) : Parcelable
