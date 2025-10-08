package org.iesch.a05_recyclerintermedio.modelo
//Creamos el modelo de datos, uso data class.

data class AndroidVersion (
    val nombre: String,
    val codigo: String,
    val apiLevel: Int,
    val anioLanzamiento: Int
)


