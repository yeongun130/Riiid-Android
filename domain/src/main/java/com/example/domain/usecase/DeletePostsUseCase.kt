package com.example.domain.usecase

import com.example.domain.repository.PostsRepository
import javax.inject.Inject

class DeletePostsUseCase @Inject constructor(
    private val postsRepository: PostsRepository
) {
    suspend operator fun invoke(id: Int) = kotlin.runCatching {
        postsRepository.deletePosts(id = id)
    }
}