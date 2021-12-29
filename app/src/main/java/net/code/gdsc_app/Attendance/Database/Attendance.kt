package net.code.gdsc_app.Attendance.Database

import androidx.room.Entity
import androidx.room.PrimaryKey
import net.code.gdsc_app.utils.Constants

@Entity(tableName = "attendance_manager_table")
data class Attendance(
    @PrimaryKey(autoGenerate = true) var id: Long = 0L,
    var attended: Long = 0L,
    var total: Long = 0L,
    var percentage: Long = 0L,
    var subject: String = "",
    var bg: Int = 0
)