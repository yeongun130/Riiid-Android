package com.example.data.repository

import com.example.data.remote.datasource.PostsDataSource
import com.example.data.remote.dto.asChangePostsRequestModel
import com.example.data.remote.dto.asPostsResponseModel
import com.example.domain.model.PostsRequestModel
import com.example.domain.model.PostsResponseModel
import com.example.domain.repository.PostsRepository
import javax.inject.Inject

class PostsRepositoryImpl @Inject constructor(
    private val postsDataSource: PostsDataSource
): PostsRepository {
    override suspend fun getPosts(): List<PostsResponseModel> =
        postsDataSource.getPosts().map { it.asPostsResponseModel() }

    override suspend fun getDetailPosts(id: Int): PostsResponseModel =
        postsDataSource.getDetailPosts(id = id).asPostsResponseModel()

    override suspend fun changePosts(
        id: Int,
        postsRequest: PostsRequestModel
    ): Unit =
        postsDataSource.changePosts(
            id = id,
            postsRequest = postsRequest.asChangePostsRequestModel()
        )

    override suspend fun deletePosts(id: Int) =
        postsDataSource.deletePosts(id = id)
}