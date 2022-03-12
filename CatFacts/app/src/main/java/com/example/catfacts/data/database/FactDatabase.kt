package com.example.catfacts.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.catfacts.data.database.dao.FactDao
import com.example.catfacts.data.database.entities.FactEntity

@Database(
    entities = [FactEntity::class],
    version = 1)
abstract class FactDatabase : RoomDatabase(){

    //por cada dao vamos a tener que hacer una funcion abstracta getAlgoDao
    abstract fun getFactDao(): FactDao
}