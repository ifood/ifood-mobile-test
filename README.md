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

# My Tweets App

## Layout
This app is based on iOS 12 guide lines. There's a scene where the user types an @user on a search bar and then can find its timeline, and each tweet of this typed user area classified in happy, neutral or sad. More info can be found on screenshot folder. 

## Structure 
Based on Android Clean Architecure, thinking further having a standardized app in both platforms. It helps to avoid bugs on the same platforms, code maintenance and easy understanding for business rules on both platforms. 

Main 4 modules: Presentation, Domain, Store, Device (which one is not being used in this project).

#### Presentation
Evolves all the components related to the UI, animation and navigation. Can be used with any architecure of presentation:  MVC, MVP , MVVM, VIP, etc. All the screen are organized in scenes. For example, with MVP, each scene has: ViewController, Presenter, Configurator (for dependency injection).

#### Domain
Evolves all the entities or models and business rules. It's independent of platform, that means, in theory it should be portable to iOS, tvOS, watchOS and linux. The interactions are encapsulated in UseCases and each UseCase executes only one interaction.

#### Store
Evolves all the manipulations that evolves temporary data, cache or persistent data. 
The Store entrypoint are the repositories that has as function to determine the access data point, decide between DB and service for e.g. The access to the DB or services are implemented in DataSources.

#### Tools
- Moya: REST easier.
- TwitterKit: Provide an easy Twitter API consumption.
- Swinject: Dependency Injection.
- SnapKit: Constraint builder for layout implementation programatically. 
- RxSwift: Reactive framework for iOS.

#### External Dependencies
Carthage was the choosen option to manage external dependencies. "Carthage builds your dependencies and provides you with binary frameworks, but you retain full control over your project structure and setup. Carthage does not automatically modify your project files or your build settings." - [Carthage](https://github.com/Carthage/Carthage)

#### Testing
No tests were implemented to this project because lack of time 😔.

#### Improvements
- Tests, of course.
- I'd suggest to the project owner to change the colors to classify the tweets, because the actual ones are not usual colors to indicate a happy, neutral or sad humour.
- Better layout treatment for iPad variation. 
- Better login and session handling. Actually the login is being done if the user tries to do a request and is not already logged in.
- Retrieve more tweets and paginate it. Also, I'd sort it by date created desc, what is not being done given API limitations.  
- Beter layout treatment for iPad variation. 
- Save in memory already searched tweets to avoid retrieving same data all the time, costing less internet connection to the final user. 
- Change loading state view to skeleton views because a skeleton view would be more friendly and beautiful. 
- Better error handling.

#### That's all, folks!
It was a great pleasure to participate on this selective proccess, I had the chance to learn a lot and improve myself a lot more. Thank you :)
