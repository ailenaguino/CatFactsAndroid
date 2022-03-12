package com.example.catfacts.domain

import com.example.catfacts.data.FactRepository
import com.example.catfacts.data.database.entities.toDatabase
import com.example.catfacts.domain.model.Fact
import javax.inject.Inject

//Este caso de uso se llama una sola vez cuando abrimos la app
class GetFactsUseCase @Inject constructor(private val repository: FactRepository) {

    //si por ejemplo estoy en otra clase y quiero hacer una instancia de getFactUseCase
    // haria val getFacts = getFactsUseCase() pero con esta funcion invoke solo tengo que hacer
    // getFactsUseCase() para llamarla
    suspend operator fun invoke(): List<Fact>{
        val facts: List<Fact> = repository.getAllFactsFromApi()

        return if(facts.isNotEmpty()){
            repository.clearFacts()
            repository.insertFacts(facts.map { it.toDatabase() } )
            facts
        }else{
            repository.getAllFactsFromDatabase()
        }
    }


}