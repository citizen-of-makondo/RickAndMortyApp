package com.alexilinskiy.rickandmortyapp.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("android:setImage")

fun setImageUrl(imageView: ImageView, imageUrl: String?) {
    Glide.with(imageView.context)
        .load(imageUrl)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(30)))
        .skipMemoryCache(false)
        .into(imageView)
}