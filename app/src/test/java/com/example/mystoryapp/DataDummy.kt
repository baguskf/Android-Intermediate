package com.example.mystoryapp

import com.example.mystoryapp.response.ListStoryItem

object DataDummy {
    fun generateDummyQuoteResponse(): List<ListStoryItem> {
        val newsList = ArrayList<ListStoryItem>()
        for (i in 0..10) {
            val news = ListStoryItem(
                "32434fer",
                "Title $i",
                "2022-02-22T22:22:22Z",
                "https://dicoding-web-img.sgp1.cdn.digitaloceanspaces.com/original/commons/feature-1-kurikulum-global-3.png",
                2.342345345,
                "erwercsdfsdgs",
                3.45446546456
            )
            newsList.add(news)
        }
        return newsList
    }
}