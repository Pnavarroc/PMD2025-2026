package org.iesch.a02_registro_superheroes.Modelo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
//5- Me creo el objeto super heroe y lo hago parcelizable osea se puede serializar.
@Parcelize
data class SuperHeroe(
    val nombre: String,
    val alterEgo: String,
    val bio: String,
    val power: Float
) : Parcelable;