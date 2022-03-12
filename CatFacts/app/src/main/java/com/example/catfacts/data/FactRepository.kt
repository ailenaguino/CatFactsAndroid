package com.example.catfacts.data

import com.example.catfacts.data.database.dao.FactDao
import com.example.catfacts.data.database.entities.FactEntity
import com.example.catfacts.data.model.FactModel
import com.example.catfacts.data.network.FactService
import com.example.catfacts.domain.model.Fact
import com.example.catfacts.domain.model.toDomain
import javax.inject.Inject

class FactRepository @Inject constructor(
    private val api: FactService,
    private val factDao : FactDao){

    suspend fun getAllFactsFromApi(): List<Fact>{
        //para convertir el factModel a fact tenemos que mapear
        //Los mapper son una funcion que nos permite convertir un objeto por capas
        //ya que el servidor nos devuelve una info pero nuestro modelo final es distinto
        val response: List<FactModel> = api.getFact()
        //map = por cada uno de los items convertimelo a dominio
        return response.map { it.toDomain() }
    }

    suspend fun getAllFactsFromDatabase(): List<Fact>{
        val response = factDao.getAllFact()
        return response.map {it.toDomain()}
    }

    suspend fun insertFacts(facts: List<FactEntity>){
        factDao.insertAllFacts(facts)
    }

    suspend fun clearFacts(){
        factDao.deleteAllFacts()
    }
}