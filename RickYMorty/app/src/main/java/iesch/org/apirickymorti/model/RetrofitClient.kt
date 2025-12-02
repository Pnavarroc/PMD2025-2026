package iesch.org.apirickymorti.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(ApiService::class.java)
}