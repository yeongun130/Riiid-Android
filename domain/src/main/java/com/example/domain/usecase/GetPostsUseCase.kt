package com.example.domain.usecase

import com.example.domain.repository.PostsRepository
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) {
    suspend operator fun invoke() = kotlin.runCatching {
        postsRepository.getPosts()
    }
}