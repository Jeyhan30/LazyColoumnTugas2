package com.example.lazycoloumntugas2.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lazycoloumntugas2.navigation.Screen
import com.example.lazycoloumntugas2.ui.components.UserCard
import com.example.lazycoloumntugas2.viewmodel.UserViewModel

@Composable
fun UserListScreen(navController: NavController) {
    val viewModel: UserViewModel = viewModel()
    val users by viewModel.users.collectAsState()


    LazyColumn(
        contentPadding = PaddingValues(15.dp)
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 25.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Nama : Jeyhan Farrel Alfarisqi", style = MaterialTheme.typography.headlineSmall)
                Text("NIM : 225150207111082", style = MaterialTheme.typography.headlineSmall)
            }
        }

        items(users) { user ->
            UserCard(user = user, onClick = {
                navController.navigate(Screen.UserDetail.createRoute(user.id))

            })
        }
    }
}
