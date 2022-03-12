package com.example.catfacts.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.catfacts.domain.model.Fact

@Entity(tableName = "fact_table")
data class FactEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "fact")
    val fact: String)

fun Fact.toDatabase() = FactEntity(fact = fact)

