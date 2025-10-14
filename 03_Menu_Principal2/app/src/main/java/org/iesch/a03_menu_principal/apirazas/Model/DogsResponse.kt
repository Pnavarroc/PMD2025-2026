package org.iesch.a03_menu_principal.apirazas.Model

import com.google.gson.annotations.SerializedName

//- Tras observar la respuesta obtenida, fabricamos la llegada del api.
data class DogsResponse(
    @SerializedName("status") var status: String,
    @SerializedName("message")var images: List<String>

    )
