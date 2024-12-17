package com.example.vehicontrol.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "login")
data class LoginEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val email: String,
    val password: String
)

