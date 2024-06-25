package com.ani.android.rest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable

fun PostItem(post: Post) {
    Column() {
        Text(text = post.title, fontSize = 15.sp)
        Text(text = post.body)
    }
}

@Composable
fun PostList(vm: ApiViewModel) {

    val state by vm.state.collectAsState(null)

    val stNrm by vm.stNrml.collectAsState()

    LazyColumn {
//        items(state ?: listOf()) {
//            PostItem(post = it)
//        }

        items(stNrm ?: listOf()) {
            PostItem(post = it)
        }
    }
}