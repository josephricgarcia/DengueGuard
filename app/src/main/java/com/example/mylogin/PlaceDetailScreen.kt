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
import com.example.mylogin.controller.PlaceDetailController
import kotlinx.coroutines.launch

@Composable
fun PlaceDetailScreen(title: String, navController: NavController, controller: PlaceDetailController) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    var updatedOccurrences by remember { mutableStateOf(List(12) { 0 }) }
    var updatedTotalCases by remember { mutableStateOf(0) }
    val isDataLoading = remember { mutableStateOf(true) }

    // Load data from the controller
    LaunchedEffect(title) {
        try {
            updatedOccurrences = controller.getOccurrences(title)
            updatedTotalCases = updatedOccurrences.sum()
        } catch (e: Exception) {
            Log.e("ControllerError", "Error fetching data: ${e.message}")
            snackbarHostState.showSnackbar("Error loading data: ${e.message}")
        } finally {
            isDataLoading.value = false
        }
    }

    Scaffold(
        bottomBar = { AdminBottomNavigationBar(navController = navController) },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (isDataLoading.value) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.w),
                    contentDescription = "Background Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "DASHBOARD",
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
                        text = "TOTAL NUMBER OF CASES",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = updatedTotalCases.toString(),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE0E0))
                    ) {
                        BarChart(updatedOccurrences) { newOccurrences ->
                            updatedOccurrences = newOccurrences
                            updatedTotalCases = newOccurrences.sum()
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Update Button
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                try {
                                    controller.updateOccurrences(title, updatedOccurrences)
                                    snackbarHostState.showSnackbar("Data updated successfully")
                                } catch (e: Exception) {
                                    Log.e("ControllerError", "Error updating data: ${e.message}")
                                    snackbarHostState.showSnackbar("An error occurred. Please try again.")
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFFE0E0),
                            contentColor = Color.Black
                        )
                    ) {
                        Text(text = "Update")
                    }
                }
            }
        }
    }
}


@Composable
fun BarChart(occurrences: List<Int>, onUpdate: (List<Int>) -> Unit) {
    val months = listOf("JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC")
    val updatedOccurrences = remember { occurrences.toMutableList() }

    Column(
        modifier = Modifier
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

        months.indices.forEach { index ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                // Display month name
                Text(
                    text = months[index],
                    fontSize = 14.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.width(16.dp))

                Box(
                    modifier = Modifier
                        .height(20.dp)
                        .fillMaxWidth(0.5f) // Adjusted to 50% width for the bar
                ) {
                    Canvas(modifier = Modifier.fillMaxHeight()) {
                        drawRect(
                            color = Color.Black,
                            size = androidx.compose.ui.geometry.Size(updatedOccurrences[index].toFloat() * 10, size.height)
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentWidth(align = Alignment.End),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    IconButton(onClick = {
                        if (updatedOccurrences[index] > 0) {
                            updatedOccurrences[index] -= 1
                            onUpdate(updatedOccurrences.toList())
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.minus),
                            contentDescription = "Decrease",
                            tint = Color.Black,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Text(
                        text = "${updatedOccurrences[index]}",
                        fontSize = 14.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )

                    IconButton(onClick = {
                        updatedOccurrences[index] += 1
                        onUpdate(updatedOccurrences.toList())
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.plus),
                            contentDescription = "Increase",
                            tint = Color.Black,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}
