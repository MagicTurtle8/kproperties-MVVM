package com.example.peter.kpropertiesmvvm;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

/**
 * Custom setters for Data Binding
 * Reference: https://developer.android.com/topic/libraries/data-binding/binding-adapters
 */
public class BindSetters {

    // Setter for glide to convert url to bitmap
    // Used to set the images for each property item
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).into(imageView);
    }
}
