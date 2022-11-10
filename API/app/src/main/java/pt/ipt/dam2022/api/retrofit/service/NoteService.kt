package pt.ipt.dam2022.api.retrofit.service

import pt.ipt.dam2022.api.model.APIResult
import pt.ipt.dam2022.api.model.Note
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * specify the part of 'url' that we want to access
 */
interface NoteService {

    /**
     * function to READ data from API
     */
    @GET("api/notes")
    fun list(): Call<List<Note>>

    /**
     * function to ADD (write) data to API
     */
    @POST("api/notes")
    fun addNote(@Body note:Note): Call<APIResult>
}


