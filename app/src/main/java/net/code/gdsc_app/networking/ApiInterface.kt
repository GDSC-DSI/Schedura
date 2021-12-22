package net.code.gdsc_app.networking

import net.code.gdsc_app.models.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("comments")
    suspend fun getPosts(
        @Query("postId") postId : Int
    ) : Response<List<User>>
}