# Twitter Mood Analyzer

## Programming language
* This application was developed using Kotlin.

## Architecture
* MVP

## Frameworks/Tools
* Retrofit2 - Make HTTP calls
* Glide - Rendering and loading images
* RxJava2 - Used with retrofit2 to return the objects from api responses as Observable objects
* Gson - Handling json objects from api call responses
* Twitter Android Kit - Used the models created on this dependency
* Mockito - Mock test variables

## Solution

MVP was used as part of this project architecture. 

The project is composed by the following set of folders:
* api - Handles api calls and expected objects from responses
* controller - Basically, there is just a controller which controls the calls for twitter and google apis and the objects which were return by them
* extension - Kotlin extensions for existing classes
* model - Keeps the model classes used by this project
* presenter - Package responsible for keep the presenter classes
* utils - AppConstants file
* view - Keeps view classes

## Challenges

There are some challenges I had to overcome for making a reasonable application.
* Learning about the APIs - Reading documentation and testing apis took a little bit time as I have never used any of those apis (Twitter and Cloud Natural Language)
* Dagger 2 - I only used dagger2 for testing purposes, on this project and started using dagger, but at certain point, I realise that was not necessary this framework and that would make creating tests harder due to I wouldn't be able to mock some classes.
