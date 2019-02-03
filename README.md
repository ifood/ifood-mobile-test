# Twitter-mood-analyzer
![CircleCi](https://img.shields.io/circleci/project/github/andersoncfsilva/twitter-mood-analyzer/master.svg?style=flat)
![Code Size](https://img.shields.io/github/languages/code-size/andersoncfsilva/twitter-mood-analyzer.svg?style=flat)
[![Kotlin version badge](https://img.shields.io/badge/kotlin-1.3.0-blue.svg)](http://kotlinlang.org/)

This app that given an Twitter username it will list user's tweets. When I tap one of the tweets the app will visualy indicate if it's a happy, neutral or sad tweet.

## Business rules
* Happy Tweet: We want a vibrant yellow color on screen with a 😃 emoji
* Neutral Tweet: We want a grey colour on screen with a 😐 emoji
* Sad Tweet: We want a blue color on screen with a 😔 emoji
* For the first release we will only support english language

## App features:
- 100% Kotlin
- Architecture components
- Vertical feature modules
- Modular views
- BDD tests
- UI tests

## Components:
#### Repository
This layer abstracts away our data sources (GoogleApi and TwitterApi)
#### Use Cases
This is where we interact with our repositories to do very specific tasks, asynchronously.
#### View Model
This makes use of the new architecture components so that we don’t need to know about our view, which you may traditionally need to know about if you was using a presenter. 
#### View
This layer comprises of our platform specific view, i.e. a fragment or an activity.
