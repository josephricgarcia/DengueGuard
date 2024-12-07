package com.example.mylogin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BottomNavigationBar(navController: NavController) {
    BottomAppBar(
        containerColor = Color(0xFFFFE0E0),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFE0E0))
            .height(80.dp) // Adjust height as needed
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFE0E0)),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButtonWithLabel(
                label = "Home",
                iconResId = R.drawable.home,
                navController = navController,
                route = "homepage"
            )
            IconButtonWithLabel(
                label = "Data",
                iconResId = R.drawable.data,
                navController = navController,
                route = "data"
            )
            IconButtonWithLabel(
                label = "Awareness",
                iconResId = R.drawable.awareness,
                navController = navController,
                route = "awareness"
            )
            IconButtonWithLabel(
                label = "Location",
                iconResId = R.drawable.loc,
                navController = navController,
                route = "loc"
            )
            IconButtonWithLabel(
                label = "Settings",
                iconResId = R.drawable.settings,
                navController = navController,
                route = "settings"
            )
        }
    }
}

@Composable
fun IconButtonWithLabel(
    label: String,
    iconResId: Int,
    navController: NavController,
    route: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(4.dp)
    ) {
        IconButton(onClick = { navController.navigate(route) }) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = label,
                tint = Color.Black
            )
        }
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black
        )
    }
}
