package com.example.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.presentation.viewmodel.GetDetailPostsViewModel

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: GetDetailPostsViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = "",
            color = Color.Black,
            fontSize = 24.sp
        )
        Spacer(modifier = modifier.height(24.dp))
        Text(
            text = "",
            color = Color.Black,
            fontSize = 16.sp
        )
    }
}

@Preview
@Composable
fun DetailScreenPre() {
    DetailScreen()
}