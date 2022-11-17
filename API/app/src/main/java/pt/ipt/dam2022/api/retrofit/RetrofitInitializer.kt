package pt.ipt.dam2022.api.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import pt.ipt.dam2022.api.retrofit.service.NoteService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * access to API
 * we must specify the URL
 */
class RetrofitInitializer {

    private val host = "https://adamastor.ipt.pt/DAM-API/"

    // set the data readed from API to be used from Retrofit
    private val gson: Gson = GsonBuilder().setLenient().create()

    private val retrofit =
        Retrofit.Builder()
                .baseUrl(host)
                .addConverterFactory(GsonConverterFactory.create(gson)).build()

    fun noteService() = retrofit.create(NoteService::class.java)

}