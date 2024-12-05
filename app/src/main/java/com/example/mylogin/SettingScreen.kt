package com.example.mylogin

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SettingScreen(navController: NavController) {
    val context = LocalContext.current as Activity
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Background Image
            Image(
                painter = painterResource(id = R.drawable.w),  // Replace with your background image
                contentDescription = "Background Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Foreground Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    // Settings Title
                    Text(
                        text = "SETTINGS",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.Black,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    // Info Section
                    Text(
                        text = "INFO",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .background(Color(0xFFFFE0E0))  // Light red background
                            .padding(10.dp),
                        textAlign = TextAlign.Start
                    )
                    // About App Link
                    ClickableText(
                        text = AnnotatedString("About App"),
                        onClick = { navController.navigate("about") },
                        modifier = Modifier
                            .padding(vertical = 4.dp),
                        style = LocalTextStyle.current.copy(
                            fontSize = 16.sp,
                            color = Color.Black,
                            textDecoration = TextDecoration.Underline
                        )
                    )
                    // Terms & Policy Link
                    ClickableText(
                        text = AnnotatedString("Terms & Policy"),
                        onClick = { navController.navigate("term") },
                        modifier = Modifier
                            .padding(vertical = 4.dp),
                        style = LocalTextStyle.current.copy(
                            fontSize = 16.sp,
                            color = Color.Black,
                            textDecoration = TextDecoration.Underline
                        )
                    )
                    ClickableText(
                        text = AnnotatedString("Login as Admin"),
                        onClick = { navController.navigate("login") },
                        modifier = Modifier
                            .padding(vertical = 4.dp),
                        style = LocalTextStyle.current.copy(
                            fontSize = 16.sp,
                            color = Color.Black,
                            textDecoration = TextDecoration.Underline
                        )
                    )
                    // Exit Link
                    ClickableText(
                        text = AnnotatedString("Exit"),
                        onClick = { context.finish() },  // Exit the app
                        modifier = Modifier
                            .padding(vertical = 4.dp),
                        style = LocalTextStyle.current.copy(
                            fontSize = 16.sp,
                            color = Color.Black,
                            textDecoration = TextDecoration.Underline
                        )
                    )
                }
            }
        }
    }
}
