package com.example.riiidandroid.module

import com.example.data.remote.datasource.PostsDataSource
import com.example.data.remote.datasource.PostsDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RemoteDataSourceModule {
    @Binds
    fun bindsPostsDataSource(postsDataSourceImpl: PostsDataSourceImpl): PostsDataSource
}