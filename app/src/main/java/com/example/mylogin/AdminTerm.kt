package com.example.mylogin

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.ExtraBold
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminTerm(navController: NavController) {
    Scaffold(
        bottomBar = { AdminBottomNavigationBar(navController = navController) }
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
                ) {Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ){

                    Icon(
                        painter = painterResource(id = R.drawable.back),
                        contentDescription = "Back",
                        modifier = Modifier.clickable { navController.navigate("setting") }
                    )


                    Spacer(modifier = Modifier.width(25.dp))

                    Text(
                        color = Color.Black,
                        text = "TERMS AND POLICY",
                        fontWeight = ExtraBold,
                        fontSize = 25.sp
                    )}
                    Spacer(modifier = Modifier.height(32.dp))
                    Text(
                        color = Color.Black,
                        text = "Dengue Guard's Terms & Policy details the guidelines and regulations regarding the use of the app.",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        color = Color.Black,
                        text = "TERMS OF USE",
                        fontSize = 20.sp,
                        fontWeight = ExtraBold,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        color = Color.Black,
                        text = "Users are required to provide accurate information.",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 15.sp,
                        textAlign = Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        color = Color.Black,
                        text = "Unauthorized use of the app is prohibited.",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 15.sp,
                        textAlign = Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        color = Color.Black,
                        text = "Users must comply with applicable laws.",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 15.sp,
                        textAlign = Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        color = Color.Black,
                        text = "PRIVACY POLICY",
                        fontSize = 20.sp,
                        fontWeight = ExtraBold,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        color = Color.Black,
                        text = "Personal data collected will be used to enhance the user experience.",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 15.sp,
                        textAlign = Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        color = Color.Black,
                        text = "Data privacy is of utmost importance to us.",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 15.sp,
                        textAlign = Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        color = Color.Black,
                        text = "Users can request data deletion by contacting support.",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 15.sp,
                        textAlign = Center,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}
