package com.example.riiidandroid.module

import com.example.data.repository.PostsRepositoryImpl
import com.example.domain.repository.PostsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindsPostsRepository(postsRepositoryImpl: PostsRepositoryImpl): PostsRepository
}