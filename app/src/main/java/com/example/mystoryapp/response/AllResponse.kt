package com.example.mystoryapp.api

import com.google.gson.annotations.SerializedName

data class RegisterResponse(

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)

data class LoginResponse(

    val loginResult: LoginResult,
    val error: Boolean,
    val message: String
)



data class LoginResult(
    val name: String,
    val userId: String,
    val token: String
)

data class ResponseDetailStory(
    val error: Boolean,
    val message: String,
    val story: Story
)

data class Story(
    val photoUrl: String,
    val createdAt: String,
    val name: String,
    val description: String,
    val lon: Any,
    val id: String,
    val lat: Any
)







