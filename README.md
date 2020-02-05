### Challenge done
[![iFood Android Challenge](https://res.cloudinary.com/marcomontalbano/image/upload/v1580874056/video_to_markdown/images/youtube--n8762R8XU6I-c05b58ac6eb4c4700831b2b3070cd403.jpg)](https://youtu.be/n8762R8XU6I "iFood Android Challenge")

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

## Getting started
- Clone this repository and import into **Android Studio**:
 - SSH: `git@github.com:ygorcesar/ifood-mobile-test.git`
 - HTTPS: `https://github.com/ygorcesar/ifood-mobile-test.git`

- Make sure to have the latest version of Android Studio
- Checkout the `master` branch from this repository
- Pull any updates from the repository
- In the welcome screen, click on `Open an existing Android Studio project` and select the folder retrieved from the repository.
## Run the code
- Just clone the repo and execute!

- Unit Tests: `./gradlew testDebug`

## Configuration
- Project configuration, version, SDK version ... are in **[configurations.gradle](buildsystem/configurations.gradle)**
- Dependencies configuration are in **[dependencies.gradle](buildsystem/dependencies.gradle)**
## Code Standards and Definitions
- Followed the **MVVM** architecture pattern
- Followed the package patterns by
 - Package by **feature**
 - Activities, Fragments, Views, Adapters... in **presentation**
 - ViewModels in **viewmodel**
 - Models in **model**
 - Persistence, network requests, raw model, mappers ... in **data**
