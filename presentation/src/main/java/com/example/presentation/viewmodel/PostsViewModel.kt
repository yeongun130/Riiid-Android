package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.PostsRequestModel
import com.example.domain.model.PostsResponseModel
import com.example.domain.usecase.ChangePostsUseCase
import com.example.domain.usecase.DeletePostsUseCase
import com.example.domain.usecase.DetailPostsUseCase
import com.example.domain.usecase.GetPostsUseCase
import com.example.presentation.exception.exceptionHandling
import com.example.presentation.viewmodel.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val getDetailPostsUseCase: DetailPostsUseCase,
    private val changePostsUseCase: ChangePostsUseCase,
    private val deletePostsUseCase: DeletePostsUseCase
): ViewModel() {

    private val _getPostsState = MutableStateFlow<Event<List<PostsResponseModel>>>(Event.Loading)
    val getPostsState = _getPostsState.asStateFlow()

    private val _getDetailPostState = MutableStateFlow<Event<PostsResponseModel>>(Event.Loading)
    val getDetailPostState = _getDetailPostState.asStateFlow()

    private val _changePostState = MutableStateFlow<Event<Unit>>(Event.Loading)
    val changePostState = _changePostState.asStateFlow()

    private val _deletePostState = MutableStateFlow<Event<Unit>>(Event.Loading)
    val deletePostState = _deletePostState.asStateFlow()

    fun getPosts() = viewModelScope.launch {
        getPostsUseCase().onSuccess {
            _getPostsState.value = Event.Success(it)
        }.onFailure {
            it.exceptionHandling(
                badRequestAction = { _getPostsState.value = Event.BadRequest }
            )
        }
    }

    fun getDetailPost(id: Int) = viewModelScope.launch {
        getDetailPostsUseCase(id = id).onSuccess {
            _getDetailPostState.value = Event.Success(it)
        }.onFailure {
            it.exceptionHandling(
                badRequestAction = { _getDetailPostState.value = Event.BadRequest }
            )
        }
    }

    fun changePost(
        id: Int,
        postsRequestModel: PostsRequestModel
    ) = viewModelScope.launch {
        changePostsUseCase(
            id = id,
            postsRequestModel = postsRequestModel
        ).onSuccess {
            _changePostState.value = Event.Success(it)
        }.onFailure {
            it.exceptionHandling(
                conflictAction = { _changePostState.value = Event.Conflict }
            )
        }
    }

    fun deletePost(id: Int) = viewModelScope.launch {
        deletePostsUseCase(id = id).onSuccess {
            val response = it.toString()

            if (response.isNotEmpty()) {
                val jsonObject = JSONObject(response)
                val code = jsonObject.getInt("code")

                if (code == 200) _deletePostState.value = Event.Accepted
            } else _deletePostState.value = Event.Success(it)
        }.onFailure {
            it.exceptionHandling(
                conflictAction = { _deletePostState.value = Event.Conflict }
            )
        }
    }
}