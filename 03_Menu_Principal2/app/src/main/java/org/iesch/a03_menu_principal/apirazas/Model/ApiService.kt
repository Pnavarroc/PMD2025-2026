package org.iesch.a03_menu_principal.apirazas.Model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

//- Creamos la interfaz, la cual contendra el método o los métodos
//Por los cuales queremos consumir nuestri api
interface ApiService {
    //- Aquí usare Retrofi y lo primero que he de poner es el tipo de operacion que realizo.
    //- Esta función recibirá por parametro algo, una dirección + loquesea/images
    //Y devolvera un objeto de tipo DogsResponse


     @GET
     fun getPerrosPorRaza( @Url url: String ) : Response<DogsResponse>



}