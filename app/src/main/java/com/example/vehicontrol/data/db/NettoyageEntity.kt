package com.example.vehicontrol.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nettoyage")
data class NettoyageEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val photoBefore: String,
    val videoBefore: String,
    val photoAfter: String,
    val videoAfter: String
)
