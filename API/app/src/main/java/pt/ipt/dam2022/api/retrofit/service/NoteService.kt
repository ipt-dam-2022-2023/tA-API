package pt.ipt.dam2022.api.retrofit.service

import pt.ipt.dam2022.api.Model.Note
import retrofit2.Call
import retrofit2.http.GET

/**
 * specify the part of 'url' that we want to access
 */
interface NoteService {
    @GET("api/notes")
    fun list(): Call<List<Note>>
}


