package com.example.mylogin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AdminBottomNavigationBar(navController: NavController) {
    BottomAppBar(
        containerColor = Color.Gray,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .height(64.dp) // Adjust height as needed
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(1.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = { navController.navigate("home") }) {
                Icon(
                    painter = painterResource(id = R.drawable.home),
                    contentDescription = "Home",
                    tint = Color.Black
                )
            }
            IconButton(onClick = { navController.navigate("datas") }) {
                Icon(
                    painter = painterResource(id = R.drawable.data),
                    contentDescription = "Data",
                    tint = Color.Black
                )
            }
            IconButton(onClick = { navController.navigate("location") }) {
                Icon(
                    painter = painterResource(id = R.drawable.loc),
                    contentDescription = "Data",
                    tint = Color.Black
                )
            }
            IconButton(onClick = { navController.navigate("setting") }) {
                Icon(
                    painter = painterResource(id = R.drawable.settings),
                    contentDescription = "Setting",
                    tint = Color.Black
                )
            }
        }
    }
}
