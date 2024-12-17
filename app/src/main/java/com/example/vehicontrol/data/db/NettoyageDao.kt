package com.example.vehicontrol.data.db

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface NettoyageDao {

    @Insert
    suspend fun insert(nettoyage: NettoyageEntity)
}