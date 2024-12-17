package com.example.vehicontrol.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AchatDao {
    @Insert
    suspend fun insert(achatEntity: AchatEntity)
}
