package net.code.gdsc_app.networking

import net.code.gdsc_app.models.Post
import retrofit2.Response

class Repository {

    suspend fun getPost(postId : Int) : Response<Post>{
        return RetrofitInstance.api.getPost(postId)
    }
}