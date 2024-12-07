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
                    modifier = Modifier.align(Alignment.Start),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(24.dp))
                Image(
                    painter = painterResource(id = R.drawable.awa),
                    contentDescription = "Mosquito Image",
                    modifier = Modifier.size(200.dp)
                )
                Spacer(modifier = Modifier.height(24.dp))

                // Section 1: What is Dengue?
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "What is Dengue?",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.weight(1f) // Text takes available width
                    )

                    Icon(
                        painter = painterResource(id = if (showDengueInfo) R.drawable.up else R.drawable.down),
                        contentDescription = if (showDengueInfo) "Collapse" else "Expand",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable { showDengueInfo = !showDengueInfo }
                    )
                }
                if (showDengueInfo) {
                    DengueInfo()
                }

                // Section 2: Dengue Symptoms
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Dengue Symptoms",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )

                    Icon(
                        painter = painterResource(id = if (showSymptoms) R.drawable.up else R.drawable.down),
                        contentDescription = if (showSymptoms) "Collapse" else "Expand",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable { showSymptoms = !showSymptoms }
                    )
                }
                if (showSymptoms) {
                    DengueSymptoms()
                }

                // Section 3: Dengue Prevention
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Dengue Prevention",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.weight(1f)
                    )

                    Icon(
                        painter = painterResource(id = if (showPrevention) R.drawable.up else R.drawable.down),
                        contentDescription = if (showPrevention) "Collapse" else "Expand",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable { showPrevention = !showPrevention }
                    )
                }
                if (showPrevention) {
                    DenguePrevention()
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

