package com.example.mylogin

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)

    fun saveOccurrences(title: String, occurrences: List<Int>) {
        sharedPreferences.edit().apply {
            occurrences.forEachIndexed { index, value ->
                putInt("${title}_occurrence_$index", value)
            }
            apply()
        }
    }

    fun loadOccurrences(title: String): List<Int> {
        return List(12) { index ->
            sharedPreferences.getInt("${title}_occurrence_$index", 0)
        }
    }
}
