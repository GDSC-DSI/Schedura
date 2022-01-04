package net.code.gdsc_app.viewmodels

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import net.code.gdsc_app.models.Query
import net.code.gdsc_app.models.Subject
import net.code.gdsc_app.networking.Repository
import net.code.gdsc_app.utils.Constants
import retrofit2.Response

class TimeTableViewModel(private val repository: Repository) : ViewModel() {

    private val _mondayData = MutableLiveData<Response<List<Subject>>>()
    private val _tuesdayData = MutableLiveData<Response<List<Subject>>>()
    private val _wednesdayData = MutableLiveData<Response<List<Subject>>>()
    private val _thursdayData = MutableLiveData<Response<List<Subject>>>()
    private val _fridayData = MutableLiveData<Response<List<Subject>>>()
    private val _saturdayData = MutableLiveData<Response<List<Subject>>>()
    val mondayData : LiveData<Response<List<Subject>>> get() = _mondayData
    val tuesdayData : LiveData<Response<List<Subject>>> get() = _tuesdayData
    val wednesdayData : LiveData<Response<List<Subject>>> get() = _wednesdayData
    val thursdayData : LiveData<Response<List<Subject>>> get() = _thursdayData
    val fridayData : LiveData<Response<List<Subject>>> get() = _fridayData
    val saturdayData : LiveData<Response<List<Subject>>> get() = _saturdayData

    fun getTimetable(query: Query, weekDay: Constants.Companion.WeekDay){
        val id = Constants.semToYearMap[query.sem]+query.branch+query.sec
        viewModelScope.launch {
            when (weekDay){
                Constants.Companion.WeekDay.MONDAY -> {
                    val response = repository.getSubjects(id,0)
                    _mondayData.value = response
                }
                Constants.Companion.WeekDay.TUESDAY -> {
                    val response = repository.getSubjects(id,1)
                    _tuesdayData.value = response
                }
                Constants.Companion.WeekDay.WEDNESDAY -> {
                    val response = repository.getSubjects(id,2)
                    _wednesdayData.value = response
                }
                Constants.Companion.WeekDay.THURSDAY -> {
                    val response = repository.getSubjects(id,3)
                    _thursdayData.value = response
                }
                Constants.Companion.WeekDay.FRIDAY -> {
                    val response = repository.getSubjects(id,4)
                    _fridayData.value = response
                }
                Constants.Companion.WeekDay.SATURDAY -> {
                    val response = repository.getSubjects(id,5)
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