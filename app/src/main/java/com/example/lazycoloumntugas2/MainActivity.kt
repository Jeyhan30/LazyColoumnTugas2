package com.example.lazycoloumntugas2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lazycoloumntugas2.navigation.Screen
import com.example.lazycoloumntugas2.ui.screen.UserDetailScreen
import com.example.lazycoloumntugas2.ui.screen.UserListScreen
import com.example.lazycoloumntugas2.ui.theme.LazyColoumnTugas2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = Screen.UserList.route) {
                composable(Screen.UserList.route) {
                    UserListScreen(navController)
                }
                composable(
                    route = Screen.UserDetail.route,
                    arguments = listOf(navArgument("userId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val userId = backStackEntry.arguments?.getInt("userId") ?: 0
                    UserDetailScreen(userId)
                }
            }
        }

    }
}
