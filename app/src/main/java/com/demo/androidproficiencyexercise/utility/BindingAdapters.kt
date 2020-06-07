package com.demo.androidproficiencyexercise.utility

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.demo.androidproficiencyexercise.R

/**
 * Created by Ashish Nair on 07/06/20.
 */

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

@BindingAdapter("imgUrl")
fun setImage(imageView: ImageView, imgUrl: String?) {
    if (imgUrl != null)
        Glide.with(imageView.context).load(imgUrl!!)
            .apply(RequestOptions.centerCropTransform().skipMemoryCache(true))
            .error(R.drawable.ic_broken_image)
            .fallback(R.drawable.ic_no_image).into(imageView)

}