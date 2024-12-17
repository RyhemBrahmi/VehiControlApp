package com.example.vehicontrol.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SuiviVehiculeDao {

    @Insert
    suspend fun insert(suiviVehicule: SuiviVehiculeEntity)

    @Query("SELECT * FROM suivi_vehicule")
    suspend fun getAll(): List<SuiviVehiculeEntity>
}