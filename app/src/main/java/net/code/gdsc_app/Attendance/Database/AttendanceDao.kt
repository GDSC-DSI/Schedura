package net.code.gdsc_app.Attendance.Database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AttendanceDao {
    @Query("SELECT * FROM attendance_manager_table")
    fun getAllProfile(): LiveData<List<Attendance>>

    @Insert
    fun insert(vararg attendance: Attendance)

    @Delete
    fun delete(vararg attendance: Attendance)

    @Update
    fun update(vararg attendance: Attendance)
}