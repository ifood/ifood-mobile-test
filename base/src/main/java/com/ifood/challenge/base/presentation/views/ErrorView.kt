package com.ifood.challenge.base.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import androidx.core.widget.NestedScrollView
import com.ifood.challenge.base.R
import com.ifood.challenge.base.common.exception.NetworkError
import com.ifood.challenge.base.extensions.getColorRes
import com.ifood.challenge.base.extensions.view.getString
import com.ifood.challenge.base.extensions.view.isVisible
import kotlinx.android.synthetic.main.error_view.view.*

class ErrorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : NestedScrollView(context, attrs, defStyleAttr) {

    init {
        val set = intArrayOf(android.R.attr.background)
        val typedArray = context.obtainStyledAttributes(attrs, set)
        val backgroundDrawable = typedArray.getDrawable(0)

        val appearance = context.obtainStyledAttributes(attrs, R.styleable.ErrorView)

        val evElevation = appearance.getDimension(
            R.styleable.ErrorView_erv_elevation,
            resources.getDimension(R.dimen.default_elevation_custom_view)
        )

        val mainContainerGravity = appearance.getInt(R.styleable.ErrorView_erv_gravity, 0)

        if (backgroundDrawable != null) background = backgroundDrawable
        else setBackgroundColor(context.getColorRes(android.R.color.white))

        View.inflate(context, R.layout.error_view, this)

        isFillViewport = true
        elevation = evElevation


        val gravity = when (mainContainerGravity) {
            1 -> Gravity.TOP
            else -> Gravity.CENTER
        }

        mainContainerError.gravity = (gravity or Gravity.CENTER_HORIZONTAL)

        appearance.recycle()
        typedArray.recycle()
    }

    fun showError(throwable: Throwable?) {
        if (throwable is NetworkError) showConnectionError()
        else showError()
    }

    fun showError(
        title: Int = R.string.action_error_title,
        subTitle: Int = R.string.error_occurred_some_problem
    ) {
        errorViewIcon.setImageResource(R.drawable.ic_error)
        errorViewTitle.text = getString(title)
        errorViewSubTitle.text = getString(subTitle)
        showView()
    }

    private fun showView() {
        isVisible = true
    }

    private fun hideError() {
        isVisible = false
    }

    fun setActionButtonClick(onButtonClick: () -> Unit) {
        errorViewButton.setOnClickListener {
            hideError()
            onButtonClick()
        }
    }

    fun showConnectionError() {
        errorViewIcon.setImageResource(R.drawable.ic_no_connection)
        errorViewTitle.text = getString(R.string.error_no_internet_connection_title)
        errorViewSubTitle.text = getString(R.string.error_no_internet_connection_subtitle)
        showView()
    }

}
