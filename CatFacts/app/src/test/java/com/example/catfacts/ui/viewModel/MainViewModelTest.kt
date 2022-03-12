package com.example.catfacts.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.catfacts.R
import com.example.catfacts.domain.GetFactsUseCase
import com.example.catfacts.domain.GetRandomFactUseCase
import com.example.catfacts.domain.GetRandomPhotoFromDrawableUseCase
import com.example.catfacts.domain.model.Fact
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest{

    @RelaxedMockK
    private lateinit var getRandomFactUseCase: GetRandomFactUseCase
    @RelaxedMockK
    private lateinit var getFactsUseCase: GetFactsUseCase
    @RelaxedMockK
    private lateinit var getRandomPhotoFromDrawableUseCase: GetRandomPhotoFromDrawableUseCase

    private lateinit var mainViewModel: MainViewModel

    //archCoreTesting nos permite testear los liveData
    //Una regla es una funcion en el onBefore pero abstraída
    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel(getFactsUseCase,getRandomFactUseCase, getRandomPhotoFromDrawableUseCase)
        //Tenemos que trucar el dispatcher ya que el viewModel trabaja con el ciclo de vida
        //que no podemos replicarlo aca
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `When view model is created for the first time, get all quotes and set the first value`() = runTest {
        //Given
        val facts = listOf(Fact("Hola"), Fact("Buen dia"))
        coEvery { getFactsUseCase() } returns facts

        //when
        mainViewModel.onCreate()

        //then
        assert(mainViewModel.factModel.value == facts.first())
    }

    @Test
    fun `when getRandomFactUseCase returns a fact then set liveData with that fact`() = runTest {
        //given
        val fact = Fact("Hola buen dia")
        coEvery { getRandomFactUseCase() } returns fact

        //when
        mainViewModel.randomFact()

        //then
        assert(mainViewModel.factModel.value == fact)
    }

    @Test
    fun `when getRandomFactUseCase returns null then keep the last fact`() = runTest {
        //Given
        val fact = Fact("Hola buen dia")
        mainViewModel.factModel.value = fact
        coEvery { getRandomFactUseCase() } returns null

        //When
        mainViewModel.randomFact()

        //then
        assert(mainViewModel.factModel.value == fact)
    }

    @Test
    fun `when getRandomPhotoFromDrawable returns a drawable then set random photo value`() = runTest {
        //given
        val drawable = R.drawable.gato1
        coEvery{ getRandomPhotoFromDrawableUseCase() } returns drawable

        //when
        mainViewModel.randomPhoto()

        //then
        assert(mainViewModel.randomPhoto.value == drawable)
    }
}