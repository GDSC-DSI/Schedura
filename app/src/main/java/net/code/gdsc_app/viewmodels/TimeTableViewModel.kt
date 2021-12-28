package net.code.gdsc_app.viewmodels

import androidx.lifecycle.*
import net.code.gdsc_app.models.Query
import net.code.gdsc_app.models.Post
import net.code.gdsc_app.networking.Repository
import net.code.gdsc_app.utils.Constants
import retrofit2.Response

class TimeTableViewModel(private val repository: Repository) : ViewModel() {

    private val _mondayData = MutableLiveData<Response<List<Post>>>()
    private val _tuesdayData = MutableLiveData<Response<List<Post>>>()
    private val _wednesdayData = MutableLiveData<Response<List<Post>>>()
    private val _thursdayData = MutableLiveData<Response<List<Post>>>()
    private val _fridayData = MutableLiveData<Response<List<Post>>>()
    private val _saturdayData = MutableLiveData<Response<List<Post>>>()
    val mondayData : LiveData<Response<List<Post>>> get() = _mondayData
    val tuesdayData : LiveData<Response<List<Post>>> get() = _tuesdayData
    val wednesdayData : LiveData<Response<List<Post>>> get() = _wednesdayData
    val thursdayData : LiveData<Response<List<Post>>> get() = _thursdayData
    val fridayData : LiveData<Response<List<Post>>> get() = _fridayData
    val saturdayData : LiveData<Response<List<Post>>> get() = _saturdayData

    fun getTimetable(query: Query, weekDay: Constants.Companion.WeekDay){
        viewModelScope.launch {
            when (weekDay){
                Constants.Companion.WeekDay.MONDAY -> {
                    val response = repository.getPosts(1)
                    _mondayData.value = response
                }
                Constants.Companion.WeekDay.TUESDAY -> {
                    val response = repository.getPosts(2)
                    _tuesdayData.value = response
                }
                Constants.Companion.WeekDay.WEDNESDAY -> {
                    val response = repository.getPosts(3)
                    _wednesdayData.value = response
                }
                Constants.Companion.WeekDay.THURSDAY -> {
                    val response = repository.getPosts(4)
                    _thursdayData.value = response
                }
                Constants.Companion.WeekDay.FRIDAY -> {
                    val response = repository.getPosts(5)
                    _fridayData.value = response
                }
                Constants.Companion.WeekDay.SATURDAY -> {
                    val response = repository.getPosts(6)
                    _saturdayData.value = response
                }
            }
        }
    }
}

class TimeTableViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TimeTableViewModel(repository) as T
    }

}