package com.devlopershankar.pregnancyvitalstracker.repository


import com.devlopershankar.pregnancyvitalstracker.data.Vital
import com.devlopershankar.pregnancyvitalstracker.data.VitalDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class VitalRepository(private val vitalDao: VitalDao) {

    val allVitals: Flow<List<Vital>> = vitalDao.getAllVitals()

    suspend fun insert(vital: Vital) {
        withContext(Dispatchers.IO) {
            vitalDao.insertVital(vital)
        }
    }
}
