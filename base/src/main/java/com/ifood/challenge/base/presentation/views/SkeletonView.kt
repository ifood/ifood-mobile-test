package com.ifood.challenge.base.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.ifood.challenge.base.R
import com.ifood.challenge.base.extensions.getColorRes
import com.ifood.challenge.base.extensions.view.ANIMATION_DURATION_LONG
import com.ifood.challenge.base.extensions.view.fadeOut
import com.ifood.challenge.base.extensions.view.isVisible
import kotlinx.android.synthetic.main.skeleton_view.view.*

class SkeletonView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        val set = intArrayOf(android.R.attr.background)
        val typedArray = context.obtainStyledAttributes(attrs, set)
        val backgroundDrawable = typedArray.getDrawable(0)

        val appearance = context.obtainStyledAttributes(attrs, R.styleable.SkeletonView)
        val skeletonLayoutId =
            appearance.getResourceId(
                R.styleable.SkeletonView_skeleton_layout,
                R.layout.skeleton_progress_bar_view
            )

        val skeletonElevation = appearance.getDimension(
            R.styleable.SkeletonView_skeleton_elevation,
            resources.getDimension(R.dimen.default_elevation_custom_view)
        )

        if (backgroundDrawable != null) background = backgroundDrawable
        else setBackgroundColor(context.getColorRes(android.R.color.white))

        View.inflate(context, R.layout.skeleton_view, this)

        elevation = skeletonElevation

        if (skeletonLayoutId != -1) {
            val skeletonLayout = View.inflate(context, skeletonLayoutId, null)
            shimmer.addView(skeletonLayout)
        }

        appearance.recycle()
        typedArray.recycle()
    }

    fun show() {
        isClickable = true
        isFocusable = true

        bringToFront()
        alpha = 1F
        isVisible = true
        shimmer.isVisible = true
        shimmer.startShimmer()
    }

    fun hide() {
        isClickable = false
        isFocusable = false
        fadeOut(ANIMATION_DURATION_LONG) {
            shimmer.stopShimmer()
            shimmer.isVisible = false
            isVisible = false
        }
    }

}
