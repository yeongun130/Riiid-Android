package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.PostsResponseModel
import com.example.domain.usecase.DetailPostsUseCase
import com.example.presentation.exception.exceptionHandling
import com.example.presentation.viewmodel.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetDetailPostsViewModel @Inject constructor(
    private val detailPostsUseCase: DetailPostsUseCase
): ViewModel() {
    private val _detailPost = MutableStateFlow<Event<PostsResponseModel>>(Event.Loading)
    val detailPost = _detailPost.asStateFlow()

    fun getDetailPost(id: Int) = viewModelScope.launch {
        detailPostsUseCase(id = id).onSuccess {
            _detailPost.value = Event.Success(it)
        }.onFailure {
            it.exceptionHandling(
                badRequestAction = { _detailPost.value = Event.BadRequest }
            )
        }
    }
}