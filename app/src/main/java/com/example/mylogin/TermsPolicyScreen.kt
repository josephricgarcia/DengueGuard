package com.example.mylogin

import android.text.Layout
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.text.style.TextAlign.Companion.Justify
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermsPolicyScreen(navController: NavController) {
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
                painter = painterResource(id = R.drawable.w),
                contentDescription = "Background Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ){

                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Back",
                            modifier = Modifier.clickable { navController.navigate("settings") }
                        )


                        Spacer(modifier = Modifier.width(25.dp))

                    Text(
                        color = Color.Black,
                        text = "TERMS AND POLICY",
                        fontWeight = FontWeight.ExtraBold,
                        style = MaterialTheme.typography.headlineMedium
                    )}
                    Spacer(modifier = Modifier.height(1.dp))
                    Text(
                        color = Color.Black,
                        text = "Dengue Guard's Terms & Policy details the guidelines and regulations regarding the use of the app.",
                        textAlign = Center,
                        modifier = Modifier.padding(horizontal = 16.dp),
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        color = Color.Black,
                        text = "TERMS OF USE",
                        fontWeight = FontWeight.ExtraBold,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        color = Color.Black,
                        text = "Users are required to provide accurate information.",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = Center,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        color = Color.Black,
                        text = "Unauthorized use of the app is prohibited.",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = Center,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        color = Color.Black,
                        text = "Users must comply with applicable laws.",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = Center,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        color = Color.Black,
                        text = "PRIVACY POLICY",
                        fontWeight = FontWeight.ExtraBold,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        color = Color.Black,
                        text = "Personal data collected will be used to enhance the user experience.",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = Center,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        color = Color.Black,
                        text = "Data privacy is of utmost importance to us.",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = Center,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        color = Color.Black,
                        text = "Users can request data deletion by contacting support.",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = Center,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}
