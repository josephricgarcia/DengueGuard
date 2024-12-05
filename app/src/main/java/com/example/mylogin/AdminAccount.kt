package com.example.mylogin

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminAccount(navController: NavController) {
    // State to control password visibility
    var isPasswordVisible by remember { mutableStateOf(false) }
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser

    Scaffold(
        topBar = {
            // TopAppBar with back button
            TopAppBar(
                title = { Text("Admin Account") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(painter = painterResource(id = R.drawable.ic_arrow_back), contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFFFFE0E0)) // Customize color if needed
            )
        },
        bottomBar = { AdminBottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Background
            Image(
                painter = painterResource(id = R.drawable.w),  // Your background image resource
                contentDescription = "Background Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Top Section
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        color = Color.Black,
                        text = "ADMIN ACCOUNT",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Display user email if logged in
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            color = Color.Black,
                            text = "Email",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            color = Color.Black,
                            text = currentUser?.email ?: "Not logged in",
                            fontSize = 18.sp
                        )
                    }

                    // Password (display masked and allow toggle)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            color = Color.Black,
                            text = "Password",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                color = Color.Black,
                                text = if (isPasswordVisible) "marian1234" else "**********",
                                fontSize = 18.sp
                            )
                            Text(
                                text = if (isPasswordVisible) "Hide" else "Show",
                                color = Color.Blue,
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .clickable { isPasswordVisible = !isPasswordVisible },
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}