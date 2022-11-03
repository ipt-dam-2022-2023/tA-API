package pt.ipt.dam2022.api.retrofit

import pt.ipt.dam2022.api.retrofit.service.NoteService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * access to API
 * we must specify the URL
 */
class RetrofitInitializer {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://adamastor.ipt.pt/DAM-API/")  // "http://10.0.2.2/"  --->  referente to an API that is on localhost
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun noteService() = retrofit.create(NoteService::class.java)

}