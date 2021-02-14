package dev.daniel.viessmannnasa.ui.main

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("vi:image_load")
fun loadImage(image: AppCompatImageView, url: String) {


    Glide.with(image.context)
        .load(url)
        .centerCrop()
        .into(image)
}