package com.example.stablestable

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.stablestable.navigation.AuthViewModel
import com.example.stablestable.navigation.SetupNavGraph
import com.example.stablestable.ui.theme.StableStableTheme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var navController: NavHostController

        super.onCreate(savedInstanceState)
        setContent {
            StableStableTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navController = rememberNavController()
                    SetupNavGraph(
                        navController = navController,
                        authViewModel = AuthViewModel())
                }
            }
        }
    }
}
