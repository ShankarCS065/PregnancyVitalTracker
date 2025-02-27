package com.devlopershankar.pregnancyvitalstracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.devlopershankar.pregnancyvitalstracker.data.VitalDatabase
import com.devlopershankar.pregnancyvitalstracker.repository.VitalRepository
import com.devlopershankar.pregnancyvitalstracker.viewmodel.VitalViewModel
import com.devlopershankar.pregnancyvitalstracker.ui.theme.PregnancyVitalsTrackerTheme
import com.devlopershankar.pregnancyvitalstracker.uiv.screen.VitalListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Ensure you have a database instance and DAO before creating the repository
        val database = VitalDatabase.getInstance(this)  // Make sure you have a singleton database
        val repository = VitalRepository(database.vitalDao())  // Pass DAO to repository

        setContent {
            VitalListScreen(repository)
        }
    }
}

