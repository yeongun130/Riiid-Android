package com.example.data.remote.network

import com.example.data.remote.dto.PostsRequest
import com.example.data.remote.dto.PostsResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface PostApi {
    @GET("posts")
    suspend fun getPosts(): List<PostsResponse>

    @GET("posts/{id}")
    suspend fun getDetailPost(
        @Path("id") id: Int
    ): PostsResponse

    @PATCH("posts/{id}")
    suspend fun changePost(
        @Path("id") id: Int,
        @Body postsRequest: PostsRequest
    )

    @DELETE("posts/{id}")
    suspend fun deletePost(
        @Path("id") id: Int
    )
}