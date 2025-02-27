package com.devlopershankar.pregnancyvitalstracker.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface VitalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVital(vital: Vital)

    @Query("SELECT * FROM vitals ORDER BY id DESC")
    fun getAllVitals(): Flow<List<Vital>>
}
