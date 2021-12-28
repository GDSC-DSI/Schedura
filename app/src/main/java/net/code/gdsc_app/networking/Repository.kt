package net.code.gdsc_app.networking

import net.code.gdsc_app.models.Post
import retrofit2.Response

class Repository {

    suspend fun getPosts(postId : Int) : Response<List<Post>>{
        return RetrofitInstance.api.getPosts(postId)
    }
}