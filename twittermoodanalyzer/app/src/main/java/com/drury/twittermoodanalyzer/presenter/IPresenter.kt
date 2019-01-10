package com.drury.twittermoodanalyzer.presenter

import com.drury.twittermoodanalyzer.view.MainActivity
import com.drury.twittermoodanalyzer.view.MoodActivity

interface IPresenter {

    interface MainPresenter {
        fun onCreate()
        fun getTweets(username: String)
        fun attachView(mainActivity: MainActivity)
        fun detachView()
    }

    interface MoodPresenter {
        fun onCreate()
        fun analyzeSentenceMood()
        fun attachView(moodActivity: MoodActivity)
        fun detachView()
    }
}