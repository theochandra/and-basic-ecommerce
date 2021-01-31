package com.android.basicecommerce.databinding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingConversion
import com.android.basicecommerce.R
import com.squareup.picasso.Picasso

@BindingConversion
fun setVisibility(state: Boolean): Int {
    return if (state) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String?) {
    url?.let { imageUrl ->
        Picasso.get().load(imageUrl).into(imageView)
    }
}

@BindingAdapter("favoriteSrc")
fun favoriteResource(imageView: ImageView, isLiked: Int) {
    if (isLiked == 1) {
        imageView.setImageResource(R.drawable.ic_action_favorite_filled)
    } else {
        imageView.setImageResource(R.drawable.ic_action_favorite)
    }
}