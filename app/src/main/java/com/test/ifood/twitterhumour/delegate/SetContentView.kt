package com.test.ifood.twitterhumour.delegate

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.annotation.LayoutRes
import com.test.ifood.twitterhumour.base.BaseActivity
import kotlin.reflect.KProperty

fun <T: ViewDataBinding> contentView(@LayoutRes layoutRes: Int) : SetContentView<T> {
    return SetContentView(layoutRes)
}

class SetContentView<out T: ViewDataBinding> (@LayoutRes private val layoutRes: Int) {

    private var value : T? = null

    operator fun getValue(thisRef: BaseActivity, property: KProperty<*>) : T {

        value = value ?: DataBindingUtil.setContentView(thisRef, layoutRes)

        return value!!
    }

}