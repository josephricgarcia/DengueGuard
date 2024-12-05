package com.example.mylogin

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun ViewerScreen(title: String, navController: NavController) {
    val firestore = FirebaseFirestore.getInstance()
    var occurrences by remember { mutableStateOf<List<Int>>(listOf()) }
    val scrollState = rememberScrollState()
    val isDataLoading = remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    // Fetch data from Firestore
    LaunchedEffect(title) {
        try {
            val document = firestore.collection("places").document(title).get().await()
            if (document.exists()) {
                val data = document.get("occurrences") as? List<Long>
                occurrences = data?.map { it.toInt() } ?: listOf()
            }
        } catch (e: Exception) {
            Log.e("FirestoreError", "Error loading data: ${e.message}")
            snackbarHostState.showSnackbar("Error loading data: ${e.message}")
        } finally {
            isDataLoading.value = false
        }
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) },
        snackbarHost = { SnackbarHost(snackbarHostState) }
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

            // Scrollable content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "VIEWER",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = title,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "CASES RESULTS",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Bar Chart
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE0E0))
                ) {
                    BarChartViewAndUpdate(occurrences) { newOccurrences ->
                        occurrences = newOccurrences
                    }
                }
            }
        }
    }
}

@Composable
fun BarChartViewAndUpdate(occurrences: List<Int>, onUpdate: (List<Int>) -> Unit) {
    val months = listOf("JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC")

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
    ) {
        Text(
            text = "CASES",
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(8.dp))

        if (occurrences.isEmpty()) {
            Text(
                text = "No data available",
                fontSize = 14.sp,
                color = Color.Black
            )
        } else {
            months.indices.forEach { index ->
                if (index < occurrences.size) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.Start, // Align items to the start
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Display Month on the left
                        Text(
                            text = months[index],
                            fontSize = 14.sp,
                            color = Color.Black
                        )

                        // Spacer to create space between month and bar chart
                        Spacer(modifier = Modifier.width(16.dp)) // Adjust the width as needed

                        // Bar Chart for the month
                        Box(
                            modifier = Modifier
                                .height(20.dp)
                                .weight(1f) // Make the bar take up remaining space
                        ) {
                            Canvas(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .fillMaxWidth(occurrences[index].toFloat() / 100f) // Adjust width according to the value
                            ) {
                                drawRect(
                                    color = Color.Black,
                                    size = androidx.compose.ui.geometry.Size(occurrences[index].toFloat() * 10, size.height)
                                )
                            }
                        }

                        // Display total number of cases for the month on the far right side
                        Text(
                            text = occurrences[index].toString(),
                            fontSize = 14.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
