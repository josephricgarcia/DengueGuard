package com.example.mylogin.model

import com.google.firebase.firestore.FirebaseFirestore

import kotlinx.coroutines.tasks.await

class PlaceDetailModel(private val firestore: FirebaseFirestore = FirebaseFirestore.getInstance()) {
    suspend fun fetchPlaceData(title: String): List<Int> {
        val document = firestore.collection("places").document(title).get().await()
        val data = document.get("occurrences") as? List<Long>
        return data?.map { it.toInt() } ?: List(12) { 0 }
    }

    suspend fun updatePlaceData(title: String, occurrences: List<Int>) {
        firestore.collection("places").document(title).update("occurrences", occurrences).await()
    }
}
