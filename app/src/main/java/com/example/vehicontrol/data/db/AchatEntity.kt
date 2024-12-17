package com.example.vehicontrol.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "achat")
data class AchatEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val montant: String,
    val quantite: String,
    val photoFacture: String
)
