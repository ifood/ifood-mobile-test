package br.com.fornaro.tweetssentiment.view.main

interface MainCallback {
    fun onEmptyUsername()
    fun onNoInternetConnection()
    fun showTweetsListScreen(username: String)
}