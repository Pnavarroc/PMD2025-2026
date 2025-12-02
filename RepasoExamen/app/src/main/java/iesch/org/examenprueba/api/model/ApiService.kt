package iesch.org.examenprueba.api.model


import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("restaurants/2/menu")
    fun getMenu(): Call<List<Pizza>>
}