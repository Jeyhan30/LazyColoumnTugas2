package com.example.lazycoloumntugas2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.lazycoloumntugas2.ui.screen.UserListScreen
import com.example.lazycoloumntugas2.ui.theme.LazyColoumnTugas2Theme
import com.example.lazycoloumntugas2.viewmodel.UserViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LazyColoumnTugas2Theme {
                val viewModel: UserViewModel = viewModel()
                UserListScreen(viewModel = viewModel)
            }
        }
    }
}
