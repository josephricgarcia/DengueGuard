package com.example.mylogin

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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

@Composable
fun AwarenessScreen(navController: NavController) {
    var showDengueInfo by remember { mutableStateOf(false) }
    var showSymptoms by remember { mutableStateOf(false) }
    var showPrevention by remember { mutableStateOf(false) }
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
                    .padding(horizontal = 16.dp, vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "DENGUE AWARENESS",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(24.dp))
                Image(
                    painter = painterResource(id = R.drawable.awa),
                    contentDescription = "Mosquito Image",
                    modifier = Modifier.size(200.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))

                // Questions
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "WHAT IS DENGUE? ",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.clickable { showDengueInfo = !showDengueInfo }
                    )
                    if (showDengueInfo) {
                        DengueInfo()
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "WHAT ARE THE SYMPTOMS OF DENGUE?",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.clickable { showSymptoms = !showSymptoms }
                    )
                    if (showSymptoms) {
                        DengueSymptoms()
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "HOW TO PREVENT DENGUE?",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.clickable { showPrevention = !showPrevention }
                    )
                    if (showPrevention) {
                        DenguePrevention()
                    }
                }
            }
        }
    }
}

@Composable
fun DengueInfo() {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE0E0)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Dengue is a mosquito-borne viral infection. It can cause severe flu-like symptoms and, in severe cases, can be fatal.",
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}

@Composable
fun DengueSymptoms() {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE0E0)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Symptoms of dengue include high fever, severe headache, pain behind the eyes, joint and muscle pain, rash, and mild bleeding.",
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}

@Composable
fun DenguePrevention() {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE0E0)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = """
                    1. Eliminate breeding sites: Remove stagnant water from containers, clean surroundings, and use larvicides.
                    2. Personal protection: Wear protective clothing, use insect repellent, and sleep under mosquito nets.
                    3. Mosquito control: Install window screens, use insecticides, and participate in community fogging.
                    4. Awareness: Educate communities, organize clean-up drives, and promote public health campaigns.
                    5. Early action: Seek medical attention for symptoms and report cases promptly.
                """.trimIndent(),
                fontSize = 16.sp,
                color = Color.Black
            )
        }
    }
}

