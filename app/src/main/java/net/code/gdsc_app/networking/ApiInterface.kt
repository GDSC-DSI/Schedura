package net.code.gdsc_app.networking

import net.code.gdsc_app.models.Subject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("gdsc")
    suspend fun getSubjects(
        @Query("id") id : String,
        @Query("day") day : Int
    ) : Response<List<Subject>>
}