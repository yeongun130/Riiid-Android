package com.example.data.local.paging

import androidx.paging.PagingSource
import com.example.data.local.datasource.LocalDataSource

class PostsPagingSource(
    private val localDataSource: LocalDataSource
) : PagingSource<Int, String>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, String> {
        return try {
            val currentPage = params.key ?: 1
            val posts = localDataSource.getPosts()

            LoadResult.Page(
                data = posts.
            )
        } catch ()
    }
}