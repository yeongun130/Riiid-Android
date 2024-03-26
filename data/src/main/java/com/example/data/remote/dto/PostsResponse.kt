package com.example.data.remote.dto

import com.example.domain.model.PostsResponseModel

data class PostsResponse(
    val id: Int,
    val title: String,
    val body: String
)

fun PostsResponse.asPostsResponseModel() = PostsResponseModel(
    id = id,
    title = title,
    body = body
)