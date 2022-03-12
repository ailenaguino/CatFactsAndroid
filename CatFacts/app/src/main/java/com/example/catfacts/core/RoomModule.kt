package com.example.catfacts.core

import android.content.Context
import androidx.room.Room
import com.example.catfacts.data.database.FactDatabase
import com.example.catfacts.data.database.dao.FactDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val FACT_DATABASE_NAME = "Fact_Database"

    //inyectamos bd
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        //Necesita el contexto, la base de datos donde esta creada y el nombre de la base de datos
        Room.databaseBuilder(context, FactDatabase::class.java, FACT_DATABASE_NAME).build()

    //inyectamos dao
    @Singleton
    @Provides
    fun provideFactDao(db: FactDatabase)= db.getFactDao()
}