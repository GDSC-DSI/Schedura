package net.code.gdsc_app.networking

import net.code.gdsc_app.models.Post
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("posts/{postId}/comments")
    suspend fun getPosts(
        @Path("postId") postId : Int
    ) : Response<List<Post>>
}