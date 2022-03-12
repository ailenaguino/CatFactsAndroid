package com.example.catfacts.data.model

import com.google.gson.annotations.SerializedName

data class FactModel (@SerializedName("text") val fact: String)