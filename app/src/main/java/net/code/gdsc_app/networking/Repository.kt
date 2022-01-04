package net.code.gdsc_app.networking

import net.code.gdsc_app.models.Subject
import retrofit2.Response

class Repository {

    suspend fun getSubjects(id : String, day : Int) : Response<List<Subject>>{
        return RetrofitInstance.api.getSubjects(id,day)
    }
}