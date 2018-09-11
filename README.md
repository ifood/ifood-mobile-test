# ifood-mobile-test
Create an app that given an Twitter username it will list user's tweets. When I tap one of the tweets the app will visualy indicate if it's a happy, neutral or sad tweet.

## Business rules
* Happy Tweet: We want a vibrant yellow color on screen with a 😃 emoji
* Neutral Tweet: We want a grey colour on screen with a 😐 emoji
* Sad Tweet: We want a blue color on screen with a 😔 emoji
* For the first release we will only support english language

### Hints
* You may use Twitter's oficial API (https://developer.twitter.com) to fetch user's tweets 
* Google's Natural Language API (https://cloud.google.com/natural-language/) may help you with sentimental analysis.

## Non functional requirements
* As this app will be a worldwide success, it must be prepared to be fault tolerant, responsive and resilient.
* Use whatever language, tools and frameworks you feel comfortable to.
* Briefly elaborate on your solution, architecture details, choice of patterns and frameworks.
* Fork this repository and submit your code.


## Language, Tools and Frameworks
* Language: Kotlin
* Target SDK: 27
* Main libraries:
    * Retrofit (with OkHttp)
    * RxJava 2
    * Dagger 2
    * Architecture Components
    * Fabric
    
## Architecture
I chose to use a Clean Architecture where the flow works like this:

Activity/Fragment -> ViewModel -> UseCase -> Repository -> Api Calls

I only needed to implement a SingleUseCase which is a UseCase that returns a RxJava Single, by default the SingleUseCase
runs in the `Schedulers.io()` and return in the Main Thread.

UseCases are allowed to call other UseCases if needed.

The communication between Activity/Fragment and ViewModel happens through LiveData properties, which tells the UI to update.
If it is a data that should be processed only once, then the object that the LiveData holds is an Event and should be listened in the UI
through an `EventObserver` (https://medium.com/google-developers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150)


 