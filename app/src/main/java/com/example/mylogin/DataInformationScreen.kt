package com.example.mylogin

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun DataInformationScreen(navController: NavController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
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

                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Text(
                    text = "DATA INFORMATION",
                    modifier = Modifier.align(Alignment.Start),
                    fontStyle = FontStyle.Normal,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )

                Image(
                    painter = painterResource(id = R.drawable.cases),
                    contentDescription = "Chart Illustration",
                    modifier = Modifier.size(150.dp)
                )
                Text(
                    text = "List Of Barangay",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(540.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent)
                ) {
                    val places = listOf(
                        "BACANI",
                        "BOGTONGBOD",
                        "BONBON",
                        "BONTUD",
                        "BUACAO",
                        "BUANGAN",
                        "CABOG",
                        "CABOY",
                        "CALUWASAN",
                        "CANDAJEC",
                        "CANTOYOC",
                        "COMAANG",
                        "DANAHAO",
                        "KATIPUNAN",
                        "LAJOG",
                        "MATAUB",
                        "NAHAWAN",
                        "POBLACION CENTRO",
                        "POBLACION NORTE",
                        "POBLACION SUR",
                        "TANGARAN",
                        "TONTUNAN",
                        "TUBOD",
                        "VILLAFLOR"
                    )
                    LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(places) { place ->
                            Button(
                                onClick = { navController.navigate("viewer/$place") },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFE0E0))
                            ) {
                                Text(text = place, fontSize = 18.sp, color = Color.Black)
                            }
                        }
                    }
                }
            }
        }
    }
}
