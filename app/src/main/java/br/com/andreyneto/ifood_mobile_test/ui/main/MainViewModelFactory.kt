package br.com.andreyneto.ifood_mobile_test.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.andreyneto.ifood_mobile_test.data.TweetRepository

class MainViewModelFactory(private val mRepository: TweetRepository):
        ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(mRepository) as T
    }
}