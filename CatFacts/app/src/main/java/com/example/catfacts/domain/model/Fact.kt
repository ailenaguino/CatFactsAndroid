package com.example.catfacts.domain.model

import com.example.catfacts.data.database.entities.FactEntity
import com.example.catfacts.data.model.FactModel

//Este va a ser el modelo de datos final con el que la UI y el dominio vana  trabajar
//entonces si hay que cambiar la bd o retrofit le da igual ya que la info que llegue al
//dominio de datos va a ser siempre esta clase
data class Fact(val fact: String)

//funcion de extension - mapeo
fun FactModel.toDomain() = Fact(fact)
fun FactEntity.toDomain() = Fact(fact)