package com.devlopershankar.pregnancyvitalstracker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.devlopershankar.pregnancyvitalstracker.data.Vital
import com.devlopershankar.pregnancyvitalstracker.repository.VitalRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class VitalViewModel(private val repository: VitalRepository) : ViewModel() {
    val allVitals = repository.allVitals

    fun addVital(bloodPressureSys: Int, bloodPressureDia: Int, weight: Float, babyKicks: Int, heartRate: Int) {
        viewModelScope.launch {
            val vital = Vital(
                bloodPressureSys = bloodPressureSys,
                bloodPressureDia = bloodPressureDia,
                weight = weight,
                babyKicks = babyKicks,
                heartRate = heartRate,
                timestamp = System.currentTimeMillis(),


            )
            repository.insert(vital)
        }
    }
    fun insert(vital: Vital) {
        viewModelScope.launch {
            repository.insert(vital)
        }
    }
}


// ViewModel Factory to properly instantiate VitalViewModel
class VitalViewModelFactory(private val repository: VitalRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VitalViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return VitalViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
