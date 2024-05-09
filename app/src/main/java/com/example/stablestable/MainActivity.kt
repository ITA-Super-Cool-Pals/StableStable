package com.example.stablestable

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.stablestable.navigation.AuthViewModel
import com.example.stablestable.navigation.SetupNavGraph
import com.example.stablestable.ui.theme.StableStableTheme
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : ComponentActivity() {
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

                    //Lykke
                    //FCM
                    FirebaseMessaging.getInstance().token
                        .addOnCompleteListener(OnCompleteListener { task ->
                            if (!task.isSuccessful) {
                                Log.d("FCM notify", "Fetching FCM registration token failed", task.exception)
                                return@OnCompleteListener
                            }
                            //Get new token
                            val token: String? = task.result
                            Log.d("FCM token", token, task.exception)
                            Toast.makeText(this, token, Toast.LENGTH_SHORT).show()
                        })
                }
            }
        }
    }
}
