package com.example.vehicontrol.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SuiviVehiculeEntity::class,NettoyageEntity::class,AchatEntity::class, LoginEntity::class ], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun suiviVehiculeDao(): SuiviVehiculeDao
    abstract fun nettoyageDao(): NettoyageDao
    abstract fun achatDao(): AchatDao
    abstract fun loginDao(): LoginDao


}