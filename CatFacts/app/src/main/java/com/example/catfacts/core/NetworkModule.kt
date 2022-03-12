package com.example.catfacts.core

import com.example.catfacts.data.network.CatFactsAPI
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    //las funciones se tienen que llamar provideAlgo
    @Singleton //le colocamos singleton para que mantenga siempre una unica instancia de Retrofit
    @Provides
    fun provideRetrofit (): Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .baseUrl("https://cat-fact.herokuapp.com/")
            .build()
    }

    @Singleton
    @Provides
    //Ya que hicimos el provide de retrofit ahora lo podemos llamar donde sea
    fun provideCatFactsAPI(retrofit: Retrofit):CatFactsAPI{
        return retrofit.create(CatFactsAPI::class.java)
    }
}