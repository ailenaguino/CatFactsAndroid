package com.example.catfacts.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catfacts.R
import com.example.catfacts.data.model.FactModel
import com.example.catfacts.domain.GetFactsUseCase
import com.example.catfacts.domain.GetRandomFactUseCase
import com.example.catfacts.domain.GetRandomPhotoFromDrawableUseCase
import com.example.catfacts.domain.model.Fact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getFactsUseCase: GetFactsUseCase,
    private val getRandomFactUseCase: GetRandomFactUseCase,
    private val getRandomPhotoFromDrawableUseCase: GetRandomPhotoFromDrawableUseCase) : ViewModel() {

    val factModel = MutableLiveData<Fact>()
    val isLoading = MutableLiveData<Boolean>()
    val randomPhoto = MutableLiveData<Int>()
    val randomUrl = MutableLiveData<String>()

    fun onCreate() {
        isLoading.postValue(true)
        //este viewMdoelScope nos permite crear una corrutina que se controla automaticamente
        viewModelScope.launch {
            val result = getFactsUseCase()

            if (!result.isNullOrEmpty()) {
                factModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }

    fun randomFact() {
        //acá fallaba porque necesita ser suspend la funcion, pero podemos arreglarlo
        //poniendo un viewModelScope.launch
        viewModelScope.launch {
            isLoading.postValue(true)
            val fact = getRandomFactUseCase()
            if (fact != null) {
                factModel.postValue(fact)
            }
            isLoading.postValue(false)
        }

    }

    fun randomPhoto() {
        /*val randomNumber = (1..5).random()
        val result = when (randomNumber) {
            1 -> R.drawable.gato1
            2 -> R.drawable.gato2
            3 -> R.drawable.gato3
            4 -> R.drawable.gato4
            else -> R.drawable.gatopng
        }*/
        viewModelScope.launch {
            val result = getRandomPhotoFromDrawableUseCase()
            randomPhoto.postValue(result)
        }
    }

    fun randomUrl(){
        val randomNumber = (1..4).random()
        val result = when(randomNumber){
            1 -> "https://i.pinimg.com/originals/46/37/63/4637632aa50dec67576174f269915e11.png"
            2 -> "https://cdn.pixabay.com/photo/2017/09/01/00/15/png-2702691_960_720.png"
            3 -> "http://assets.stickpng.com/thumbs/58b061138a4b5bbbc8492951.png"
            else -> "http://assets.stickpng.com/images/580b57fbd9996e24bc43bb8f.png"
        }
        randomUrl.postValue(result)
    }
}