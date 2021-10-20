package com.alexilinskiy.rickandmortyapp.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("android:setImage")
fun setImageUrl(imageView: ImageView, imageUrl: String?) {
    Glide.with(imageView.context)
        .load(imageUrl)
        .into(imageView)
}