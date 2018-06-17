# Tweet Mood - iFood mobile test

## Goal
Create an app that given an Twitter username it will list user's tweets. When I tap one of the tweets the app will visualy indicate if it's a happy, neutral or sad tweet.

## Solution

This project was developed in Java using the MVVM standard with LiveData. Some of the principles of clean architecture were also used to delimit responsibilities and make single, separating the interest of each module and maintaining the business rules without knowing any detail about the outside world.

### Twitter connection

To connect with Twitter we used the client Search Tweets API. This api returns a collection of relevant Tweets matching a specified query, in this case, the query was a match between the the user's text imputed and the Twitter username. For example, if the user types NASA, the search will look for @NASA.

### Google Sentimental Analysis

To consume Google services an api rest was created using Retrofit and OkHttp. This choice was made due to the low complexity of the elements used for calls on Google Cloud Natural Language services.

## Main Frameworks and libraries
* Lombok: reduce boilerplate and create getters, setters, builders and others
* Data binding and butterKnife: used to bind the layouts and boilerplate reduction.
* Picasso: manipulation of images
* Retrofit, Okhttp and Gson: responsible for http connections and object conversions
* Dagger2: making dependency injection