package com.devlopershankar.pregnancyvitalstracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vitals")
data class Vital(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val heartRate: Int,
    val bloodPressureSys: Int,
    val bloodPressureDia: Int,
    val weight: Float,
    val babyKicks: Int,
    val timestamp: Long // Store date & time as a timestamp
)
