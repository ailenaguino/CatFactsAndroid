package com.example.catfacts.domain

import com.example.catfacts.data.FactRepository
import com.example.catfacts.domain.model.Fact
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetRandomFactUseCaseTest{

    @RelaxedMockK
    private lateinit var factRepository: FactRepository

    private lateinit var getRandomFactUseCase: GetRandomFactUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getRandomFactUseCase = GetRandomFactUseCase(factRepository)
    }

    @Test
    fun `when All Facts From Database Returns EmptyList Then Get Null`() = runBlocking {
        //Given
        coEvery { factRepository.getAllFactsFromDatabase() } returns emptyList()
        //When
        val response = getRandomFactUseCase()
        //Then
        assert( response == null)
    }

    @Test
    fun `when All Facts From Database Returns A List Then Get Random Fact`() = runBlocking {
        //Given
        val myList = listOf(Fact("Hola buen dia"))
        coEvery { factRepository.getAllFactsFromDatabase() } returns myList
        //When
        val response = getRandomFactUseCase()
        //Then
        assert(response == myList.first())
    }
}