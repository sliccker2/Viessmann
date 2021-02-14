package dev.daniel.viessmannnasa.ui.largeImage


import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("vi:image_load_large")
fun loadImage(image: AppCompatImageView, url: String) {

    Glide.with(image.context)
        .load(url)

        .into(image)

}
