package com.example.catfacts.domain

import com.example.catfacts.data.FactRepository
import com.example.catfacts.domain.model.Fact
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class GetFactsUseCaseTest{

    @RelaxedMockK //sirve para que si nosotros no definimos una de las respuestas de la clase que
    //estamos creando, nos la va a generar mockk. En cambio si usamos @Mockk solo, si la clase
    //accede a algo que no preparamos, va a fallar el test
    private lateinit var factRepository: FactRepository //del instanciado se encarga mockk

    private lateinit var getFactsUseCase: GetFactsUseCase

    //todo lo que se haga aca adentro se va a ejecutar antes de los test
    @Before
    fun onBefore(){
        //inicializo libreria de mockk
        MockKAnnotations.init(this)
        getFactsUseCase = GetFactsUseCase(factRepository)
    }

    @Test
    fun `when The Api Doesnt Return Anything Then Get Values From Database`()
    = runBlocking{ //gestiona las corrutinas
        //Given
        //Cuando se llame a esta funcion de este mock retorna una emptylist
        //el co de Every es porque el metodo es una corrutina
        coEvery {factRepository.getAllFactsFromApi()} returns emptyList()

        //When
        getFactsUseCase()

        //Then
        coVerify(exactly = 1) { factRepository.getAllFactsFromDatabase() }
    }

    @Test
    fun `when The Api Returns A List Then Get Values From Api`() = runBlocking{
        //Given
        val myList = listOf(Fact("Hola buen dia"))
        coEvery { factRepository.getAllFactsFromApi() } returns myList

        //When
        val response = getFactsUseCase()

        //Then
        coVerify(exactly = 1) { factRepository.clearFacts() }
        coVerify(exactly = 1) { factRepository.insertFacts(any()) }
        coVerify(exactly = 0) { factRepository.getAllFactsFromDatabase() }
        assert(response == myList)
    }
}