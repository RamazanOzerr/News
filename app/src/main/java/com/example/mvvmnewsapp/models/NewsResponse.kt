package com.example.mvvmnewsapp.models

import com.example.mvvmnewsapp.models.Article

data class NewsResponse(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)