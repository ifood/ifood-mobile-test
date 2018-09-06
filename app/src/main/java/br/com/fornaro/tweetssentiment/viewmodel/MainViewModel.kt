package br.com.fornaro.tweetssentiment.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import br.com.fornaro.tweetssentiment.utils.InternetUtils
import br.com.fornaro.tweetssentiment.view.main.MainCallback

class MainViewModel(application: Application) : AndroidViewModel(application) {

    var callback: MainCallback? = null

    fun seeTweetsButtonClick(username: String) {
        if (username.isEmpty()) {
            callback?.onEmptyUsername()
        } else if (!InternetUtils.isNetworkAvailable(getApplication())) {
            callback?.onNoInternetConnection()
        } else {
            callback?.showTweetsListScreen(username)
        }
    }
}