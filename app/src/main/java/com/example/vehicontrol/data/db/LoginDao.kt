package com.example.vehicontrol.data.db

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface LoginDao {
    @Insert
    suspend fun insert(loginEntity: LoginEntity)
}
