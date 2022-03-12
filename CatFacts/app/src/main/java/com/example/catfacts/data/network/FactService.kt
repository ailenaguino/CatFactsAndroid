package com.example.catfacts.data.network

import com.example.catfacts.core.RetrofitHelper
import com.example.catfacts.data.model.FactModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

//esta clase será la puerta de acceso a internet y dicha puerta será llamada por el repositorio
//si un día queremos cambiar los endpoints solo deberemos tocar esta clase y el resto de nuestra app quedará intacta.
class FactService @Inject constructor(private val api: CatFactsAPI //esto nos daria la api con retrofit todo junto
) {
    suspend fun getFact(): List<FactModel> {
        //ejecutar la llamada en un hilo secundario para no saturar la interfaz del usuario(hilo principal)
        return withContext(Dispatchers.IO){
            val response = api.getRandomFact()
            response.body() ?: emptyList()
        }

    }
}