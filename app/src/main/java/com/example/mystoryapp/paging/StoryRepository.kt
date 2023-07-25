package com.example.mystoryapp.paging

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.mystoryapp.UserPreference
import com.example.mystoryapp.api.ApiService
import com.example.mystoryapp.response.ListStoryItem

class StoryRepository(private val apiService: ApiService, private val pref: UserPreference) {
    fun getStories(): LiveData<PagingData<ListStoryItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                StoryPagingSource(apiService, pref)
            }
        ).liveData
    }
}