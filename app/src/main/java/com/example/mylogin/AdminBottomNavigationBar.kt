package com.example.mylogin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun AdminBottomNavigationBar(navController: NavController) {
    BottomAppBar(
        containerColor = Color(0xFFFFE0E0),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFE0E0))
            .height(80.dp) // Adjust height as needed
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(
                iconId = R.drawable.home,
                label = "Home",
                onClick = { navController.navigate("home") }
            )
            BottomNavItem(
                iconId = R.drawable.data,
                label = "Data",
                onClick = { navController.navigate("datas") }
            )
            BottomNavItem(
                iconId = R.drawable.loc,
                label = "Location",
                onClick = { navController.navigate("location") }
            )
            BottomNavItem(
                iconId = R.drawable.settings,
                label = "Settings",
                onClick = { navController.navigate("setting") }
            )
        }
    }
}

@Composable
fun BottomNavItem(iconId: Int, label: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = label,
            tint = Color.Black,
            modifier = Modifier.size(24.dp) // Adjust icon size if needed
        )
        Text(
            text = label,
            color = Color.Black,
            fontSize = 12.sp, // Adjust font size if needed
            textAlign = TextAlign.Center
        )
    }
}