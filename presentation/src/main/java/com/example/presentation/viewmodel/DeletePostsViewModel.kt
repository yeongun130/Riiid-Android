package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.DeletePostsUseCase
import com.example.presentation.exception.exceptionHandling
import com.example.presentation.viewmodel.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class DeletePostsViewModel @Inject constructor(
    private val deletePostsUseCase: DeletePostsUseCase
): ViewModel() {
    private val _deletePost = MutableStateFlow<Event<Unit>>(Event.Loading)
    val deletePost = _deletePost.asStateFlow()

    fun DeletePost(id: Int) = viewModelScope.launch {
        deletePostsUseCase(id = id).onSuccess {
            val response = it.toString()

            if (response.isNotEmpty()) {
                val jsonObject = JSONObject(response)
                val code = jsonObject.getInt("code")

                if (code == 200) _deletePost.value = Event.Accepted
            } else _deletePost.value = Event.Success(it)
        }.onFailure {
            it.exceptionHandling(
                conflictAction = { _deletePost.value = Event.Conflict }
            )
        }
    }
}