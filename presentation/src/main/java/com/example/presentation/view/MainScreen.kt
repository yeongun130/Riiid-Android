package com.example.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.viewmodel.PostsViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: PostsViewModel = hiltViewModel()
) {
    val getPosts by viewModel.getPostsState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getPosts()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn {
            item(getPosts.data!!.size) {
                Text(
                    text = "",
                    color = Color.Black,
                    fontSize = 24.sp
                )
                Spacer(modifier = modifier.height(16.dp))
                Text(
                    text = "",
                    color = Color.Black,
                    fontSize = 24.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPre() {

}