package br.com.tweetanalyzer.view

import android.annotation.SuppressLint
import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.Toolbar
import android.util.AttributeSet
import android.view.View
import br.com.tweetanalyzer.R
import de.hdodenhof.circleimageview.CircleImageView


/**
 * Created by gabrielsamorim
 * on 17/05/18.
 */
class AvatarImageBehavior(var mContext: Context, attrs: AttributeSet?) : CoordinatorLayout.Behavior<CircleImageView>() {
    private var mCustomFinalYPosition = 0f
    private var mCustomStartXPosition= 0f
    private var mCustomStartToolbarPosition= 0f
    private var mCustomStartHeight= 0f
    private var mCustomFinalHeight= 0f

    private var mAvatarMaxSize= 0f
    private var mFinalLeftAvatarPadding: Float
    private var mStartXPosition= 0
    private var mStartToolbarPosition= 0f
    private var mStartYPosition: Int = 0
    private var mFinalYPosition: Int = 0
    private var mStartHeight: Int = 0
    private var mFinalXPosition: Int = 0
    private var mChangeBehaviorPoint = 0f

    init {
        if (attrs != null) {
            val a = mContext.obtainStyledAttributes(attrs, R.styleable.AvatarImageBehavior)
            mCustomFinalYPosition = a.getDimension(R.styleable.AvatarImageBehavior_finalYPosition, 0f)
            mCustomStartXPosition = a.getDimension(R.styleable.AvatarImageBehavior_startXPosition, 0f)
            mCustomStartToolbarPosition = a.getDimension(R.styleable.AvatarImageBehavior_startToolbarPosition, 0f)
            mCustomStartHeight = a.getDimension(R.styleable.AvatarImageBehavior_startHeight, 0f)
            mCustomFinalHeight = a.getDimension(R.styleable.AvatarImageBehavior_finalHeight, 0f)

            a.recycle()
        }

        init()
        mFinalLeftAvatarPadding = mContext.resources.getDimension(R.dimen.spacing_normal)
    }

    private fun init() {
        bindDimensions()
    }

    private fun bindDimensions() {
        mAvatarMaxSize = mContext.resources.getDimension(R.dimen.image_width)
    }

    override fun layoutDependsOn(parent: CoordinatorLayout, child: CircleImageView, dependency: View): Boolean {
        return dependency is Toolbar
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: CircleImageView, dependency: View): Boolean {
        maybeInitProperties(child, dependency)

        val maxScrollDistance = mStartToolbarPosition.toInt()
        val expandedPercentageFactor = dependency.y / maxScrollDistance

        if (expandedPercentageFactor < mChangeBehaviorPoint) {
            val heightFactor = (mChangeBehaviorPoint - expandedPercentageFactor) / mChangeBehaviorPoint

            val distanceXToSubtract = (mStartXPosition - mFinalXPosition) * heightFactor + child.height / 2
            val distanceYToSubtract = (mStartYPosition - mFinalYPosition) * (1f - expandedPercentageFactor) + child.height / 2

            child.x = mStartXPosition - distanceXToSubtract
            child.y = mStartYPosition - distanceYToSubtract

            val heightToSubtract = (mStartHeight - mCustomFinalHeight) * heightFactor

            val lp = child.layoutParams as CoordinatorLayout.LayoutParams
            lp.width = (mStartHeight - heightToSubtract).toInt()
            lp.height = (mStartHeight - heightToSubtract).toInt()
            child.layoutParams = lp
        } else {
            val distanceYToSubtract = (mStartYPosition - mFinalYPosition) * (1f - expandedPercentageFactor) + mStartHeight / 2

            child.x = (mStartXPosition - child.width / 2).toFloat()
            child.y = mStartYPosition - distanceYToSubtract

            val lp = child.layoutParams as CoordinatorLayout.LayoutParams
            lp.width = mStartHeight
            lp.height = mStartHeight
            child.layoutParams = lp
        }
        return true
    }

    @SuppressLint("PrivateResource")
    private fun maybeInitProperties(child: CircleImageView, dependency: View) {
        if (mStartYPosition == 0)
            mStartYPosition = dependency.y.toInt()

        if (mFinalYPosition == 0)
            mFinalYPosition = dependency.height / 2

        if (mStartHeight == 0)
            mStartHeight = child.height

        if (mStartXPosition == 0)
            mStartXPosition = (child.x + child.width / 2).toInt()

        if (mFinalXPosition == 0)
            mFinalXPosition = mContext.resources.getDimensionPixelOffset(R.dimen.abc_action_bar_content_inset_material) + mCustomFinalHeight.toInt() / 2

        if (mStartToolbarPosition == 0f)
            mStartToolbarPosition = dependency.y

        if (mChangeBehaviorPoint == 0f) {
            mChangeBehaviorPoint = (child.height - mCustomFinalHeight) / (2f * (mStartYPosition - mFinalYPosition))
        }
    }
}