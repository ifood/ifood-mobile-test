package br.com.fornaro.tweetssentiment.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import br.com.fornaro.tweetssentiment.repository.TweetsRepository

class TweetsViewModel(application: Application) : AndroidViewModel(application) {

    private val tweetsRepository = TweetsRepository()
}