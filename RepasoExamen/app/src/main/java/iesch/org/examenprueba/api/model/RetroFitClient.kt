package iesch.org.examenprueba.api.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitClient {


    private val retrofit = Retrofit.Builder()
        .baseUrl("https://private-anon-907ce3bea4-pizzaapp.apiary-mock.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(ApiService::class.java)
}