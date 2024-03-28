package com.example.data.local.datasource

import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun getPosts(): Flow<String>
}