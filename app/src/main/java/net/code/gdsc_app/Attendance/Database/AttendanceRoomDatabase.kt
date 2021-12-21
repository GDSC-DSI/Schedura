package net.code.gdsc_app.Attendance.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Attendance::class], version = 1)
abstract class AttendanceRoomDatabase : RoomDatabase() {

    abstract fun attendanceDao(): AttendanceDao

    companion object {
        @Volatile
        private var INSTANCE: AttendanceRoomDatabase? = null

        fun getDatabase(context: Context): AttendanceRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AttendanceRoomDatabase::class.java,
                    "Attendance_Database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}