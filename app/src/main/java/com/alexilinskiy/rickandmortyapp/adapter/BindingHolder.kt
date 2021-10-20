package com.alexilinskiy.rickandmortyapp.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BindingHolder(v: View) : RecyclerView.ViewHolder(v) {
    val binding: ViewDataBinding? = DataBindingUtil.bind(v)
}