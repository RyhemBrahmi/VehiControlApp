package com.example.vehicontrol.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.vehicontrol.data.api.AchatService
import com.example.vehicontrol.data.api.ApiService
import com.example.vehicontrol.data.api.LoginService
import com.example.vehicontrol.data.api.NettoyageService
import com.example.vehicontrol.data.db.AchatDao
import com.example.vehicontrol.data.db.AppDatabase
import com.example.vehicontrol.data.db.LoginDao
import com.example.vehicontrol.data.db.NettoyageDao
import com.example.vehicontrol.data.db.SuiviVehiculeDao
import com.example.vehicontrol.data.repository.AchatRepositoryImpl
import com.example.vehicontrol.data.repository.LoginRepositoryImpl
import com.example.vehicontrol.data.repository.NettoyageRepositoryImpl
import com.example.vehicontrol.data.repository.SuiviVehiculeRepositoryImpl
import com.example.vehicontrol.domain.repository.AchatRepository
import com.example.vehicontrol.domain.repository.LoginRepository
import com.example.vehicontrol.domain.repository.NettoyageRepository
import com.example.vehicontrol.domain.repository.SuiviVehiculeRepository
import com.example.vehicontrol.domain.usecase.SubmitAchatUseCase
import com.example.vehicontrol.domain.usecase.SubmitLoginUseCase
import com.example.vehicontrol.domain.usecase.SubmitNettoyageUseCase
import com.example.vehicontrol.domain.usecase.SubmitSuiviVehiculeUseCase
import com.example.vehicontrol.util.Constants.BASE_URL
import dagger.Module
import dagger.hilt.android.qualifiers.ApplicationContext

import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    val MIGRATION_3_4 = object : Migration(3, 4) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("""
            CREATE TABLE IF NOT EXISTS suivi_vehicule (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                kilometrage TEXT NOT NULL,
                carburant TEXT NOT NULL,
                photoPath TEXT NOT NULL,
                videoPath TEXT NOT NULL
            )
        """)

            database.execSQL("""
            CREATE TABLE IF NOT EXISTS nettoyage (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,  -- Ajout de la contrainte NOT NULL sur id
                photoBefore TEXT NOT NULL,
                videoBefore TEXT NOT NULL,
                photoAfter TEXT NOT NULL,
                videoAfter TEXT NOT NULL
            )
        """)

            database.execSQL("""
            CREATE TABLE IF NOT EXISTS achat (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,  -- Ajout de la contrainte NOT NULL sur id
                montant TEXT NOT NULL,
                quantite TEXT NOT NULL,
                photoFacture TEXT NOT NULL
            )
        """)
            database.execSQL("""
            CREATE TABLE IF NOT EXISTS login (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,  -- Ajout de la contrainte NOT NULL sur id
                email TEXT NOT NULL,
                password TEXT NOT NULL
            )
        """)
        }
    }



    @Provides
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
            .addMigrations(MIGRATION_3_4)
            .build()
    }

    @Provides
    fun provideSuiviVehiculeDao(database: AppDatabase): SuiviVehiculeDao {
        return database.suiviVehiculeDao()
    }

    @Provides
    fun provideSuiviVehiculeRepository(
        apiService: ApiService,
        suiviVehiculeDao: SuiviVehiculeDao
    ): SuiviVehiculeRepository {
        return SuiviVehiculeRepositoryImpl(apiService, suiviVehiculeDao)
    }

    @Provides
    fun provideSubmitSuiviVehiculeUseCase(repository: SuiviVehiculeRepository): SubmitSuiviVehiculeUseCase {
        return SubmitSuiviVehiculeUseCase(repository)
    }

    @Provides
    fun provideNettoyageService(): NettoyageService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NettoyageService::class.java)
    }


    @Provides
    fun provideNettoyageDao(database: AppDatabase): NettoyageDao {
        return database.nettoyageDao()
    }

    @Provides
    fun provideNettoyageRepository(
        nettoyageService: NettoyageService,
        nettoyageDao: NettoyageDao
    ): NettoyageRepository {
        return NettoyageRepositoryImpl(nettoyageService, nettoyageDao)
    }

    @Provides
    fun provideSubmiNettoyageUseCase(repository: NettoyageRepository): SubmitNettoyageUseCase {
        return SubmitNettoyageUseCase(repository)
    }

    @Provides
    fun provideAchatService(): AchatService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AchatService::class.java)
    }


    @Provides
    fun provideAchatDao(database: AppDatabase): AchatDao {
        return database.achatDao()
    }

    @Provides
    fun provideAchatRepository(
        achatService: AchatService,
        achatDao: AchatDao
    ): AchatRepository {
        return AchatRepositoryImpl(achatService, achatDao)
    }

    @Provides
    fun provideSubmiAchatUseCase(repository: AchatRepository): SubmitAchatUseCase {
        return SubmitAchatUseCase(repository)
    }

    //
    @Provides
    fun provideLoginService(): LoginService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginService::class.java)
    }


    @Provides
    fun provideLoginDao(database: AppDatabase): LoginDao {
        return database.loginDao()
    }

    @Provides
    fun provideLoginRepository(
        loginService: LoginService,
    ): LoginRepository {
        return LoginRepositoryImpl(loginService)
    }

    @Provides
    fun provideSubmitLoginUseCase(repository: LoginRepository): SubmitLoginUseCase {
        return SubmitLoginUseCase(repository)
    }
}