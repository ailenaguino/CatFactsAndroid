package com.example.catfacts.domain

import com.example.catfacts.data.FactRepository
import com.example.catfacts.data.model.FactModel
import com.example.catfacts.domain.model.Fact
import javax.inject.Inject

class GetRandomFactUseCase @Inject constructor(private val repository: FactRepository){


    suspend operator fun invoke(): Fact?{
        val facts = repository.getAllFactsFromDatabase()
        if(!facts.isNullOrEmpty()){
            val randomNumber = (0..facts.size-1).random()
            return facts[randomNumber]
        }
        return null
    }
}