package com.example.data.remote.datasource

import com.example.data.remote.dto.PostsRequest
import com.example.data.remote.dto.PostsResponse
import okhttp3.ResponseBody

interface PostsDataSource {
    suspend fun getPosts(): List<PostsResponse>

    suspend fun getDetailPosts(id: Int): PostsResponse

    suspend fun changePosts(
        id: Int,
        postsRequest: PostsRequest
    )

    suspend fun deletePosts(id: Int)
}