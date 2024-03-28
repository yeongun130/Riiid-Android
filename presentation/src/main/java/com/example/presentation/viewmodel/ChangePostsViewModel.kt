package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.PostsRequestModel
import com.example.domain.usecase.ChangePostsUseCase
import com.example.presentation.exception.exceptionHandling
import com.example.presentation.viewmodel.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePostsViewModel @Inject constructor(
    private val changePostsUseCase: ChangePostsUseCase
): ViewModel() {
    private val _changePostState = MutableStateFlow<Event<Unit>>(Event.Loading)
    val changePostState = _changePostState.asStateFlow()

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
}