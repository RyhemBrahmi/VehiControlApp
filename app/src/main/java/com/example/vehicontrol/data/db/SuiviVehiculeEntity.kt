package com.example.vehicontrol.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "suivi_vehicule")
data class SuiviVehiculeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val kilometrage: String,
    val carburant: String,
    val photoPath: String,
    val videoPath: String
)