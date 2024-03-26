package com.example.domain.repository

import com.example.domain.model.PostsRequestModel
import com.example.domain.model.PostsResponseModel

interface PostsRepository {
    suspend fun getPosts(): List<PostsResponseModel>

    suspend fun getDetailPosts(id: Int): PostsResponseModel

    suspend fun changePosts(
        id: Int,
        postsRequest: PostsRequestModel
    )

    suspend fun deletePosts(id: Int)
}