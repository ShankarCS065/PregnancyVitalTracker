package com.devlopershankar.pregnancyvitalstracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Vital::class], version = 2)
abstract class VitalDatabase : RoomDatabase() {
    abstract fun vitalDao(): VitalDao

    companion object {
        @Volatile
        private var INSTANCE: VitalDatabase? = null

        fun getInstance(context: Context): VitalDatabase {
            return INSTANCE ?: synchronized(this) {
                val database = Room.databaseBuilder(
                    context.applicationContext,
                    VitalDatabase::class.java,
                    "vital_database"
                ) .fallbackToDestructiveMigration().build()
                INSTANCE = database
                database
            }
        }
    }
}
