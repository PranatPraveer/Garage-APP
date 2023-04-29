# Garage-APP
Garage APP is a convinience app for maintaining your garage.

### Download and Test the app [here](https://drive.google.com/file/d/19_o-BrU0WsFcGbqmHWxWyhcKUT29q6QF/view?usp=sharing)

## Built using - 
- Kotlin Programming Language
- Hilt for dependency Injection
- Retrofit Library for Network call
- Room db 
- Android Studio

## Features of the app - 
- The App uses MVVM architecure & coroutines to asynchronously call the API. 
- This App is based on single activity multiple fragment concept.
- In this App  you can add photos of your vehicle along with the model name and maker name of you car.

## Working of the app - 
<img src="https://user-images.githubusercontent.com/68765059/233559504-aa20f62a-ad86-4194-8c6d-14961cb45127.mp4" height = "400" width="200">

## Concepts used - 
- **MVVM Architecture** : Followed clean architecture and MVVM design pattern. Followed the respository pattern where API calls happen through repostiory and it becomes the single source of truth for the app. The ViewModels can access the repostiory and then provide the Livedata to the fragments to observe.
- **Dependency Injection** : Used Hilt library for dependency injection, made a Network Module and Database Module class which provides instance of API and Database to Hilt. 
- **Coroutines** : Used coroutines to asynchronously call the API in background. 
- **Retrofit Library** : Used the Retrofit library which is type-safe HTTP client for Android for interacting with API.

## Decisions & Assumptions -
- Used the RecyclerView List View to display all the cars on the homescreen. 
- Used the Retrofit library instead of the Volley library.
- Did not work properly on the UI as time was pretty less so tried to make it as minimalistic as possible. 
- For adding photo camera app opens to add photo clicked at that time only.
