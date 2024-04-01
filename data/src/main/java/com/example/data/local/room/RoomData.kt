package com.example.data.local.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import com.example.data.remote.dto.PostsResponse

@Entity("posts")
data class PostsEntity(
    @PrimaryKey val id: Int,
    val title: String
)

@Dao
interface PostDao {
    @Query("SELECT * FROM posts")
    fun pagingSource(): PagingSource<Int, PostsEntity>
}

@Database(entities = [PostsEntity::class], version = 1)
abstract class DataBase: RoomDatabase() {
    abstract fun postDao(): PostDao
}
