package com.example.data.remote.dto

import com.example.domain.model.PostsRequestModel

data class PostsRequest(
    val title: String,
    val body: String
)

fun PostsRequestModel.asChangePostsRequestModel() = PostsRequest(
    title = title,
    body = body
)