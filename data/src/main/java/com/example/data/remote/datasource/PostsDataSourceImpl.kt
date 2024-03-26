package com.example.data.remote.datasource

import com.example.data.remote.dto.PostsRequest
import com.example.data.remote.dto.PostsResponse
import com.example.data.remote.network.PostApi
import com.example.data.remote.util.safeApiCall
import okhttp3.ResponseBody
import javax.inject.Inject

class PostsDataSourceImpl @Inject constructor(
    private val postApi: PostApi
): PostsDataSource {
    override suspend fun getPosts(): List<PostsResponse> = safeApiCall {
        postApi.getPosts()
    }

    override suspend fun getDetailPosts(id: Int): PostsResponse = safeApiCall {
        postApi.getDetailPost(id = id)
    }

    override suspend fun changePosts(
        id: Int,
        postsRequest: PostsRequest
    ) = safeApiCall {
        postApi.changePost(
            id = id,
            postsRequest = postsRequest
        )
    }

    override suspend fun deletePosts(id: Int) = safeApiCall {
        postApi.deletePost(id = id)
    }
}