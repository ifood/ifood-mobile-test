package com.drury.twittermoodanalyzer.presenter

interface IPresenter {

    interface MainPresenter {
        fun onCreate()
        fun getTweets(username: String)
    }

    interface MoodPresenter {
        fun onCreate()
        fun analyzeSentenceMood()
    }
}