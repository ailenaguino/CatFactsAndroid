package com.example.catfacts.domain

import com.example.catfacts.R
import javax.inject.Inject

class GetRandomPhotoFromDrawableUseCase @Inject constructor(){

    suspend operator fun invoke(): Int{
        val drawables = listOf(R.drawable.gato1, R.drawable.gato2,
            R.drawable.gato3, R.drawable.gato4, R.drawable.gatopng)
        val randomNumber = (0..drawables.size-1).random()
        return drawables[randomNumber]
        }
    }