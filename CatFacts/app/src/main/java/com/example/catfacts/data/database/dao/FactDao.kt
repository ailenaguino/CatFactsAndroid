package com.example.catfacts.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.catfacts.data.database.entities.FactEntity

@Dao
interface FactDao {

    @Query("SELECT * FROM fact_table")
    suspend fun getAllFact(): List<FactEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllFacts(facts: List<FactEntity>)

    @Query("DELETE FROM fact_table")
    suspend fun deleteAllFacts()

}