package com.spider.twitteranalyzer.feature.list.viewslice.search

import android.app.Activity
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.OnLifecycleEvent
import android.view.MenuItem
import com.github.florent37.tutoshowcase.TutoShowcase
import com.miguelcatalan.materialsearchview.MaterialSearchView
import com.spider.twitteranalyzer.base.extension.findViewById
import com.spider.twitteranalyzer.base.test.EspressoIdlingResource
import com.spider.twitteranalyzer.base.viewslice.BaseViewSlice
import com.spider.twitteranalyzer.base.viewslice.ViewSlice
import com.spider.twitteranalyzer.feature.list.R
import javax.inject.Inject


interface SearchViewSlice : ViewSlice {

    fun setMenuItem(item: MenuItem?)
    fun getAction(): LiveData<Action>

    sealed class Action {
        data class UserNameSubmitted(val userName: String) : Action()
    }

    class Impl @Inject constructor(
        private val actionLiveData: MutableLiveData<Action>,
        private val showcaseEnabled: Boolean = true
    ) : BaseViewSlice(), SearchViewSlice {

        override fun getAction(): LiveData<Action> = actionLiveData
        private var showcase: TutoShowcase? = null

        private val searchView by lazy {
            context.findViewById<MaterialSearchView>(R.id.search_view)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun onCreate() {
            setUpSearchView()
            if (showcaseEnabled)
                setUpShowCaseToSearchView()
        }

        private fun setUpSearchView() {
            searchView?.setVoiceSearch(true)
            searchView?.setHint(context.getString(R.string.username_search_hint))
            searchView?.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    EspressoIdlingResource.increment()
                    actionLiveData.value = Action.UserNameSubmitted(query)
                    return false
                }
            })
        }

        private fun setUpShowCaseToSearchView() {
            showcase = TutoShowcase.from(context as Activity)
                .setContentView(R.layout.tutorial_list)
                .on(R.id.fake_search) //a view in actionbar
                .addCircle()
                .withBorder()
                .onClickContentView(R.id.btn_got_it) { showcase?.dismiss() }
                .showOnce("tutorial.search")
        }

        override fun setMenuItem(item: MenuItem?) {
            searchView?.setMenuItem(item)
        }
    }

}