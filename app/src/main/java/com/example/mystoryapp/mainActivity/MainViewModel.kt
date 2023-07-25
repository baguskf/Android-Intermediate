package com.example.mystoryapp.mainActivity

import androidx.lifecycle.*
import com.example.mystoryapp.UserModel
import com.example.mystoryapp.UserPreference
import com.example.mystoryapp.UserToken

import com.example.mystoryapp.response.ListStoryItem
import kotlinx.coroutines.launch


class MainViewModel ( private val pref: UserPreference) : ViewModel() {


    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            pref.logout()
        }
    }

}