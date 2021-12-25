package net.code.gdsc_app.networking

import net.code.gdsc_app.models.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("posts/{postId}")
    suspend fun getPost(
        @Path("postId") postId : Int
    ) : Response<Post>
}