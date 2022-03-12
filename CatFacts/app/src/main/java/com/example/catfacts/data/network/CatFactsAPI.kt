package com.example.catfacts.data.network

import com.example.catfacts.data.model.FactModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface CatFactsAPI {

    @GET("facts/")
    suspend fun getRandomFact() : Response<List<FactModel>>
}