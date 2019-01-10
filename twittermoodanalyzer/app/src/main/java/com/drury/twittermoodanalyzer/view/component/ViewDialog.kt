package com.drury.twittermoodanalyzer.view.component

import android.app.Activity
import android.app.Dialog
import android.view.Window
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.drury.twittermoodanalyzer.R


class ViewDialog(var activity: Activity) {

    lateinit var dialog: Dialog

    fun showDialog() {

        dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_loading_view)

        val gifImageView = dialog.findViewById<ImageView>(R.id.custom_loading_imageView)

        val imageViewTarget = DrawableImageViewTarget(gifImageView)

        Glide.with(activity)
                .load(R.drawable.twitterfly)
                .apply(RequestOptions().placeholder(R.drawable.twitterfly).centerCrop())
                .into(imageViewTarget)

        dialog.show()
    }

    fun hideDialog() {
        dialog.dismiss()
    }

}