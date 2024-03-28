package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.PostsResponseModel
import com.example.domain.usecase.GetPostsUseCase
import com.example.presentation.exception.exceptionHandling
import com.example.presentation.viewmodel.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetPostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
): ViewModel() {
    private val _getPostsState = MutableStateFlow<Event<List<PostsResponseModel>>>(Event.Loading)
    val getPostsState = _getPostsState.asStateFlow()

    fun getPosts() = viewModelScope.launch {
        getPostsUseCase().onSuccess {
            _getPostsState.value = Event.Success(it)
        }.onFailure {
            it.exceptionHandling(
                badRequestAction = { _getPostsState.value = Event.BadRequest }
            )
        }
    }
}