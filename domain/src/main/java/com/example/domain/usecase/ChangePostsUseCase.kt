package com.example.domain.usecase

import com.example.domain.model.PostsRequestModel
import com.example.domain.repository.PostsRepository
import javax.inject.Inject

class ChangePostsUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) {
    suspend operator fun invoke(
        id: Int,
        postsRequestModel: PostsRequestModel
    ) = kotlin.runCatching {
        postsRepository.changePosts(
            id = id,
            postsRequest = postsRequestModel
        )
    }
}