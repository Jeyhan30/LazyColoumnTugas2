package com.example.lazycoloumntugas2.ui.screen

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lazycoloumntugas2.viewmodel.UserDetailViewModel
import com.example.lazycoloumntugas2.viewmodel.UserDetailViewModelFactory
import com.example.lazycoloumntugas2.data.network.RetrofitInstance
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDetailScreen(userId: Int) {
    val factory = UserDetailViewModelFactory(RetrofitInstance.api)
    val viewModel: UserDetailViewModel = viewModel(factory = factory)

    val userDetail = viewModel.userDetail
    val isLoading = viewModel.isLoading
    val error = viewModel.error

    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    LaunchedEffect(Unit) {
        viewModel.fetchUserDetail(userId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("User Detail") },
                navigationIcon = {
                    IconButton(onClick = { backDispatcher?.onBackPressed() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                error != null -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Error: $error", color = MaterialTheme.colorScheme.error)
                    }
                }

                userDetail != null -> {
                    Card(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = userDetail.name,
                                style = MaterialTheme.typography.headlineSmall,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            Divider()

                            Spacer(modifier = Modifier.height(8.dp))
                            InfoItem(label = "Username", value = userDetail.username)
                            InfoItem(label = "Email", value = userDetail.email)
                            InfoItem(label = "Phone", value = userDetail.phone)
                            InfoItem(label = "Website", value = userDetail.website)

                            Spacer(modifier = Modifier.height(8.dp))
                            Divider()
                            Spacer(modifier = Modifier.height(8.dp))

                            Text("Address", style = MaterialTheme.typography.titleMedium)
                            InfoItem(label = "Street", value = userDetail.address.street)
                            InfoItem(label = "City", value = userDetail.address.city)

                            Spacer(modifier = Modifier.height(8.dp))
                            Divider()
                            Spacer(modifier = Modifier.height(8.dp))

                            Text("Company", style = MaterialTheme.typography.titleMedium)
                            InfoItem(label = "Name", value = userDetail.company.name)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun InfoItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "$label:", fontSize = 14.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(text = value, fontSize = 14.sp)
    }
}
