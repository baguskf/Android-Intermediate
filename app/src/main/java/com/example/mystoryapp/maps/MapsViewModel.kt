package com.example.mystoryapp.maps

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.mystoryapp.UserPreference
import com.example.mystoryapp.UserToken

class MapsViewModel( private val pref: UserPreference): ViewModel() {

    fun getUserData(): LiveData<UserToken> {
        return pref.getUserToken().asLiveData()
    }
}