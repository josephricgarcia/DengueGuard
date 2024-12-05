package com.example.mylogin

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.TileOverlayOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.maps.android.compose.*
import com.google.maps.android.heatmaps.HeatmapTileProvider
import com.google.maps.android.heatmaps.WeightedLatLng
import kotlinx.coroutines.tasks.await


@Composable
fun MapScreen(navController: NavController) {
    val clarinLocation = LatLng( 9.959309,  124.034823) // Clarin, Bohol
    val firestore = FirebaseFirestore.getInstance()

    var locationsWithCases by remember { mutableStateOf<List<WeightedLatLng>>(emptyList()) }
    var totalCases by remember { mutableStateOf(0) }
    val isDataLoading = remember { mutableStateOf(true) }

    // State to keep track of the current zoom level
    val cameraPositionState = rememberCameraPositionState {
        position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(
            clarinLocation, 13f
        )
    }

    LaunchedEffect(Unit) {
        fetchDataFromFirestore(firestore) { casesList, total ->
            locationsWithCases = casesList
            totalCases = total
            isDataLoading.value = false
        }
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Create HeatMap from weighted locations
            val heatmapProvider = remember(locationsWithCases, cameraPositionState.position.zoom) {
                if (locationsWithCases.isNotEmpty()) {
                    val zoomLevel = cameraPositionState.position.zoom
                    val adjustedRadius = (zoomLevel * 2).toInt() // Adjust the radius dynamically based on zoom

                    HeatmapTileProvider.Builder()
                        .weightedData(locationsWithCases)
                        .radius(adjustedRadius) // Adjust radius based on zoom
                        .build()
                } else {
                    Log.w("MapScreen", "No data available for heatmap")
                    null
                }
            }

            // Display Google Map with HeatMap overlay
            GoogleMap(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(800.dp),
                cameraPositionState = cameraPositionState,
                uiSettings = MapUiSettings(
                    zoomControlsEnabled = false,
                    scrollGesturesEnabled = false,
                    zoomGesturesEnabled = false,
                    tiltGesturesEnabled = false,
                ),
                properties = MapProperties(
                    minZoomPreference = 10f,
                    maxZoomPreference = 20f
                )
            ) {
                heatmapProvider?.let { provider ->
                    MapEffect(provider) { map ->
                        map.clear()
                        val tileOverlay = TileOverlayOptions().tileProvider(provider)
                        map.addTileOverlay(tileOverlay)
                        Log.d("MapScreen", "Heatmap overlay added with zoom ${cameraPositionState.position.zoom}")
                    }
                }
            }
        }
    }
}

// Fetch data from Firestore and prepare WeightedLatLng list
private suspend fun fetchDataFromFirestore(
    firestore: FirebaseFirestore,
    onResult: (List<WeightedLatLng>, Int) -> Unit
) {
    try {
        val placesCollection = firestore.collection("places").get().await()
        val casesList = mutableListOf<WeightedLatLng>()
        var totalCases = 0

        for (document in placesCollection.documents) {
            val placeName = document.id
            val occurrences = document.get("occurrences") as? List<Long>
            val cases = occurrences?.sumOf { it.toInt() } ?: 0
            totalCases += cases

            val location = placesLatLngMap[placeName]
            if (location != null && cases > 0) {
                casesList.add(WeightedLatLng(location, cases.toDouble()))
            }
            Log.d("MapScreen", "Place: $placeName, Cases: $cases, Location: $location")
        }

        onResult(casesList, totalCases)
    } catch (e: Exception) {
        Log.e("MapScreen", "Error loading data: ${e.message}")
    }
}

// Map of place names to their respective LatLng coordinates
private val placesLatLngMap = mapOf(
    "BACANI" to LatLng(9.96711, 124.04052),
    "BOGTONGBOD" to LatLng(9.93403, 124.0556),
    "BONBON" to LatLng(9.96396, 124.01782),
    "BONTUD" to LatLng(9.94678, 124.03456),
    "BUACAO" to LatLng(9.95284, 124.00311),
    "BUANGAN" to LatLng(9.93231, 124.02477),
    "CABOG" to LatLng(9.9136, 124.04842),
    "CABOY" to LatLng(9.9492, 124.0604),
    "CALUWASAN" to LatLng(9.9575, 124.0753),
    "CANDAJEC" to LatLng(9.9494, 124.02402),
    "CANTOYOC" to LatLng(9.96406, 124.06091),
    "COMAANG" to LatLng(9.96657, 9.96657),
    "DANAHAO" to LatLng(9.9125, 124.0254),
    "KATIPUNAN" to LatLng(9.9435, 124.0097),
    "LAJOG" to LatLng(9.95307, 124.00905),
    "MATAUB" to LatLng(9.95093, 124.05297),
    "NAHAWAN" to LatLng(9.97058, 124.04706),
    "POBLACION CENTRO" to LatLng(9.9622, 124.0262),
    "POBLACION NORTE" to LatLng(9.9574, 124.0237),
    "POBLACION SUR" to LatLng(9.9569, 124.0262),
    "TANGARAN" to LatLng(9.9642, 124.0309),
    "TONTUNAN" to LatLng(9.9389, 124.0497),
    "TUBOD" to LatLng(9.95, 124.01667),
    "VILLAFLOR" to LatLng(9.9611, 124.0703)
)
