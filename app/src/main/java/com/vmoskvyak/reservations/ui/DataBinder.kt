package com.vmoskvyak.reservations.ui

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageResource")
fun setImageResource(imageView: ImageView, resource: Int) {
    val context = imageView.context

    Glide.with(context)
            .load(resource)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(imageView)

}
