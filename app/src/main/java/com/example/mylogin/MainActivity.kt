package com.example.mylogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mylogin.controller.PlaceDetailController
import com.example.mylogin.model.PlaceDetailModel
import com.example.mylogin.ui.theme.MyLoginTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase
        FirebaseApp.initializeApp(this)

        // Firestore instance
        val firestore = FirebaseFirestore.getInstance()

        // Initialize the PlaceDetailModel
        val placeDetailModel = PlaceDetailModel(firestore)

        // Initialize the PlaceDetailController
        val placeDetailController = PlaceDetailController(placeDetailModel)

        setContent {
            MyLoginTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "start") {

                    composable("start") { StartingScreen(navController = navController) }
                    composable("homepage") { HomePageScreen(navController = navController) }
                    composable("data") { DataInformationScreen(navController = navController) }
                    composable("awareness") { AwarenessScreen(navController = navController) }
                    composable("loc") { MapScreen(navController = navController) }
                    composable("settings") { SettingScreen(navController = navController) }
                    composable("about") { AboutAppScreen(navController = navController) }
                    composable("term") { TermsPolicyScreen(navController = navController) }
                    composable("login") { AdminLoginScreen(navController = navController) }
                    composable("forgot") { ForgotPasswordScreen(navController = navController) }
                    composable("register") { AdminRegisterScreen(navController = navController) }

                    composable("placeDetail/{place}") { backStackEntry ->
                        val place = backStackEntry.arguments?.getString("place") ?: "Unknown Place"
                        PlaceDetailScreen(
                            title = place,
                            navController = navController,
                            controller = placeDetailController // Pass controller here
                        )
                    }

                    composable("viewer/{place}") { backStackEntry ->
                        val title = backStackEntry.arguments?.getString("place") ?: "Default Title"
                        ViewerScreen(title = title, navController = navController)
                    }

                    // Other composables for different screens
                    composable("home") { HomePage(navController = navController) }
                    composable("datas") { DataScreen(navController = navController) }
                    composable("location") { AdminLoc(navController = navController) }
                    composable("setting") { AdminSetting(navController = navController) }
                    composable("aboutapp") { AdminAboutApp(navController = navController) }
                    composable("Term") { AdminTerm(navController = navController) }
                    composable("acc") { AdminAccount(navController = navController) }
                }
            }
        }
    }
}



