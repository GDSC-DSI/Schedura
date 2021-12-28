package net.code.gdsc_app.Attendance.Database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class AttendanceViewmodel(application: Application) : AndroidViewModel(Application()) {

    private var parentJob = Job()
    private val scope = CoroutineScope(parentJob + Dispatchers.Main)

    private val repository: AttendanceRepository
    val allLists: LiveData<List<Attendance>>

    init {
        val attendanceDao = AttendanceRoomDatabase.getDatabase(application).attendanceDao()
        repository = AttendanceRepository(attendanceDao)
        allLists = repository.allLists
    }

    fun insert(attendance: Attendance) = scope.launch(Dispatchers.IO) {
        repository.insert(attendance)
    }

    fun delete(attendance: Attendance) = scope.launch(Dispatchers.IO) {
        repository.delete(attendance)
    }

    fun update(attendance: Attendance) = scope.launch(Dispatchers.IO) {
        repository.update(attendance)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}