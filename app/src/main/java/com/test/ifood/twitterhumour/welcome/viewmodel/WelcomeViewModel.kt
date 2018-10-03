package com.test.ifood.twitterhumour.welcome.viewmodel

import android.app.Application
import android.databinding.Bindable
import com.test.ifood.twitterhumour.BR
import com.test.ifood.twitterhumour.base.BaseView
import com.test.ifood.twitterhumour.base.BaseViewModel

class WelcomeViewModel(application: Application) : BaseViewModel<BaseView?>(application) {

    var userName: String = ""
        set(value) {
            if (field != value) {
                field = value
                registry.notifyChange(this, BR._all)
            }
        }

    val buttonEnabled: Boolean
        @Bindable get() {
            return !userName.isEmpty()
        }

}