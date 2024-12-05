package com.example.mylogin

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.TileOverlayOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.maps.android.compose.*
import com.google.maps.android.heatmaps.HeatmapTileProvider
import com.google.maps.android.heatmaps.WeightedLatLng
import kotlinx.coroutines.tasks.await

@Composable
fun AdminLoc(navController: NavController) {
    val clarinLocation = LatLng(9.959309,  124.034823) // Coordinates of Clarin, Bohol
    val firestore = FirebaseFirestore.getInstance()

    var locationsWithCases by remember { mutableStateOf<List<WeightedLatLng>>(emptyList()) }
    var totalCases by remember { mutableStateOf(0) }
    val isDataLoading = remember { mutableStateOf(true) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            clarinLocation,
            13f
        )
    }

    // Fetch data from Firestore
    LaunchedEffect(Unit) {
        try {
            val placesCollection = firestore.collection("places").get().await()
            val casesList = mutableListOf<WeightedLatLng>()
            var totalCases = 0

            for (document in placesCollection.documents) {
                val placeName = document.id
                val occurrences = document.get("occurrences") as? List<Long>
                val cases = occurrences?.sumOf { it.toInt() } ?: 0
                totalCases += cases

                val location = getLatLngForPlace(placeName)
                if (location != null && cases > 0) {
                    casesList.add(WeightedLatLng(location, cases.toDouble()))
                }
                Log.d("AdminLoc", "Place: $placeName, Cases: $cases, Location: $location")
            }

            locationsWithCases = casesList
            totalCases = totalCases
            isDataLoading.value = false
        } catch (e: Exception) {
            Log.e("AdminLoc", "Error loading data: ${e.message}")
        }
    }

    Scaffold(
        bottomBar = { AdminBottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Create HeatMap from weighted locations, dynamically adjusting the radius based on zoom level
            val heatmapProvider = remember(locationsWithCases, cameraPositionState.position.zoom) {
                if (locationsWithCases.isNotEmpty()) {
                    val zoomLevel = cameraPositionState.position.zoom
                    val adjustedRadius = (zoomLevel * 2).toInt().coerceIn(10, 50) // Adjust dynamically and constrain range
                    HeatmapTileProvider.Builder()
                        .weightedData(locationsWithCases)
                        .radius(adjustedRadius) // Adjust radius based on zoom
                        .build()
                } else {
                    Log.w("AdminLoc", "No data for heatmap")
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
            ) {
                heatmapProvider?.let { provider ->
                    MapEffect(provider) { map ->
                        map.clear()
                        val tileOverlay = TileOverlayOptions().tileProvider(provider)
                        map.addTileOverlay(tileOverlay)
                        Log.d("AdminLoc", "Heatmap overlay added with zoom ${cameraPositionState.position.zoom}")
                    }
                }
            }
        }
    }
}


// This function maps place names to their respective LatLng coordinates
fun getLatLngForPlace(place: String): LatLng? {
    return when (place.trim().toUpperCase()) {
        "BACANI" -> LatLng(9.96711, 124.04052)
        "BOGTONGBOD" -> LatLng(9.93403, 124.0556)
        "BONBON" -> LatLng(9.96396, 124.01782)
        "BONTUD" -> LatLng(9.94678, 124.03456)
        "BUACAO" -> LatLng(9.95284, 124.00311)
        "BUANGAN" -> LatLng(9.93231, 124.02477)
        "CABOG" -> LatLng(9.9136, 124.04842)
        "CABOY" -> LatLng(9.9492, 124.0604)
        "CALUWASAN" -> LatLng(9.9575, 124.0753)
        "CANDAJEC" -> LatLng(9.9494, 124.02402)
        "CANTOYOC" -> LatLng(9.96406, 124.06091)
        "COMAANG" -> LatLng(9.96657, 124.06657)
        "DANAHAO" -> LatLng(9.9125, 124.0254)
        "KATIPUNAN" -> LatLng(9.9435, 124.0097)
        "LAJOG" -> LatLng(9.95307, 124.00905)
        "MATAUB" -> LatLng(9.95093, 124.05297)
        "NAHAWAN" -> LatLng(9.97058, 124.04706)
        "POBLACION CENTRO" -> LatLng(9.9622, 124.0262)
        "POBLACION NORTE" -> LatLng(9.9574, 124.0237)
        "POBLACION SUR" -> LatLng(9.9569, 124.0262)
        "TANGARAN" -> LatLng(9.9642, 124.0309)
        "TONTUNAN" -> LatLng(9.9389, 124.0497)
        "TUBOD" -> LatLng(9.95, 124.01667)
        "VILLAFLOR" -> LatLng(9.9611, 124.0703)
        else -> null
    }
}
