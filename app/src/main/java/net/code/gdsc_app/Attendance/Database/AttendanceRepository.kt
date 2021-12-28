package net.code.gdsc_app.Attendance.Database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class AttendanceRepository(private val attendanceDao: AttendanceDao) {

    val allLists: LiveData<List<Attendance>> = attendanceDao.getAllProfile()

    @WorkerThread
    fun insert(attendance: Attendance) {
        attendanceDao.insert(attendance)
    }

    @WorkerThread
    fun delete(attendance: Attendance) {
        attendanceDao.delete(attendance)
    }

    @WorkerThread
    fun update(attendance: Attendance) {
        attendanceDao.update(attendance)
    }
}