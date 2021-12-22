package net.code.gdsc_app.viewmodels

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import net.code.gdsc_app.models.User
import net.code.gdsc_app.networking.Repository
import retrofit2.Response

class TimeTableViewModel(private val repository: Repository) : ViewModel() {

    private val _user = MutableLiveData<Response<List<User>>>();
    val user : LiveData<Response<List<User>>> get() = _user

    fun getPosts(postId : Int){
        viewModelScope.launch {
            val response = repository.getPosts(postId)
            _user.value = response
        }
    }
}

class TimeTableViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TimeTableViewModel(repository) as T
    }
}