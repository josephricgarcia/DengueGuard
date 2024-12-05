package com.example.mylogin.controller

import com.example.mylogin.model.PlaceDetailModel


class PlaceDetailController(private val placeDetailModel: PlaceDetailModel) {

    // Fetch occurrences for a given place
    suspend fun getOccurrences(title: String): List<Int> {
        return placeDetailModel.fetchPlaceData(title)
    }

    // Update occurrences for a given place
    suspend fun updateOccurrences(title: String, occurrences: List<Int>) {
        placeDetailModel.updatePlaceData(title, occurrences)
    }
}
