package com.ifood.challenge.base.presentation.views

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.NestedScrollView
import com.ifood.challenge.base.R
import com.ifood.challenge.base.extensions.getColorRes
import com.ifood.challenge.base.extensions.view.isVisible
import kotlinx.android.synthetic.main.empty_view.view.*

class EmptyView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : NestedScrollView(context, attrs, defStyleAttr) {

    init {
        val set = intArrayOf(android.R.attr.background)
        val typedArray = context.obtainStyledAttributes(attrs, set)
        val backgroundDrawable = typedArray.getDrawable(0)

        val appearance = context.obtainStyledAttributes(attrs, R.styleable.EmptyView)

        val actionButtonVisibility =
            appearance.getInt(R.styleable.EmptyView_ev_action_button_visibility, 0)

        val title =
            appearance.getResourceId(R.styleable.EmptyView_ev_title, R.string.action_error_title)
        val subtitle = appearance.getResourceId(
            R.styleable.EmptyView_ev_subtitle,
            R.string.empty_view_no_information_found
        )
        val actionText = appearance.getResourceId(
            R.styleable.EmptyView_ev_action_button_text,
            R.string.search_again
        )
        val icon = appearance.getResourceId(R.styleable.EmptyView_ev_icon, R.drawable.ic_empty)

        val evElevation = appearance.getDimension(
            R.styleable.EmptyView_ev_elevation,
            resources.getDimension(R.dimen.default_elevation_custom_view)
        )

        val mainContainerGravity = appearance.getInt(R.styleable.EmptyView_ev_gravity, 0)

        if (backgroundDrawable != null) background = backgroundDrawable
        else setBackgroundColor(context.getColorRes(android.R.color.white))

        View.inflate(context, R.layout.empty_view, this)

        isFillViewport = true
        elevation = evElevation

        emptyViewButton.visibility = if (actionButtonVisibility == 0) View.VISIBLE else View.GONE
        emptyViewTitle.setText(title)
        emptyViewSubTitle.setText(subtitle)
        emptyViewButton.setText(actionText)
        emptyViewIcon.setImageDrawable(ContextCompat.getDrawable(context, icon))

        val gravity = when (mainContainerGravity) {
            1 -> Gravity.TOP
            else -> Gravity.CENTER
        }

        emptyViewMainContainer.gravity = (gravity or Gravity.CENTER_HORIZONTAL)

        appearance.recycle()
        typedArray.recycle()
    }

    private fun hideView() {
        isVisible = false
    }

    fun setActionButtonClick(hideErrorOnClick: Boolean = true, onButtonClick: () -> Unit) {
        emptyViewButton.setOnClickListener {
            if (hideErrorOnClick) hideView()
            onButtonClick()
        }
    }
}
