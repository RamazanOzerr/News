package com.example.mvvmnewsapp.util

// Resource class is recommended by google to control responses
// it allows us to handle the success, error and loading states

// sealed class is like an abstract class
// but it allows you to choose which classes can inherit from it
sealed class Resource<T>(
    val data: T ?= null,
    val message: String ?= null
) {
    // these are the classes that inherit from Resource class
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class loading<T> : Resource<T>()

}