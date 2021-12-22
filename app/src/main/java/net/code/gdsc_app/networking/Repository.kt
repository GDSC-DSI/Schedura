package net.code.gdsc_app.networking

import net.code.gdsc_app.models.User
import retrofit2.Response

class Repository {

    suspend fun getPosts(postId : Int) : Response<List<User>>{
        return RetrofitInstance.api.getPosts(postId)
    }
}