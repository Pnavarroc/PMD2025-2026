package iesch.org.apirickymorti.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    //- Aquí usare Retrofi y lo primero que he de poner es el tipo de operacion que realizo.
    //- Esta función recibirá por parametro algo, una dirección + loquesea/images
    //Y devolvera un objeto de tipo CharacterResponse
    @GET("character")
    suspend fun getCharacters() : CharacterResponse
}