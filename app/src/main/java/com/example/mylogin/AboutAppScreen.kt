package com.example.mylogin

import android.R.attr.onClick
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.ExtraBold
import androidx.compose.ui.text.style.TextAlign.Companion.Justify
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutAppScreen(navController: NavController) {
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
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.Start
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
                        text = "ABOUT DENGUE GUARD",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 25.sp
                    )}


                    Text(
                        color = Color.Black,
                        text = "Dengue Guard is an app designed to enhance the monitoring and management of dengue outbreaks.",
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        color = Color.Black,
                        text = "Key Features:",
                        fontWeight = ExtraBold,
                        style = MaterialTheme.typography.titleLarge,
                        fontSize = 15.sp,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        color = Color.Black,
                        text = "1. Dengue Awareness: Provides crucial information on dengue prevention, symptoms, and treatment.",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = Justify,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        color = Color.Black,
                        text = "2. Dashboard for Case Monitoring: Displays real-time data on dengue cases.",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = Justify,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        color = Color.Black,
                        text = "3. Heatmap for Outbreak Localization: Visual representation of dengue cases across different regions.",
                        style = MaterialTheme.typography.bodySmall,
                        textAlign = Justify,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

            }
        }
    }
}
