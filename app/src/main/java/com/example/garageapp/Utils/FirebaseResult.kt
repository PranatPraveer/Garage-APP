package com.example.garageapp.Utils

sealed class FirebaseResult<T>(val data:T?=null, val message:String?=null){
    class Success<T>(data:T):FirebaseResult<T>(data)
    class Error<T>(message:String?,data:T?=null):FirebaseResult<T>(data,message)

    class Loading<T>: FirebaseResult<T>()
}