package com.example.catfacts.core

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.load(url: String){
    if(url.isNotEmpty()){
        //cualquier componente de la ui si le ponemos .context te devuelve el contexto
        Glide.with(this.context).load(url).into(this)
    }
}