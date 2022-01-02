package net.code.gdsc_app.Attendance.Adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import net.code.gdsc_app.Attendance.Database.Attendance
import net.code.gdsc_app.Attendance.Database.AttendanceViewmodel
import net.code.gdsc_app.R
import net.code.gdsc_app.databinding.ItemAttendanceBinding
import net.code.gdsc_app.utils.Constants

class AttendanceAdapter(
    val attendanceViewmodel: AttendanceViewmodel,
    val parentView: View,
    val activity: FragmentActivity?
) :
    ListAdapter<Attendance, AttendanceAdapter.ViewHolder>(
        ListDiffCallbacks()
    ) {

    var attendanceList = ArrayList<Attendance>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAttendanceBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, attendanceViewmodel)
//        if (position == 0) {
//            introDelete(holder.binding.itemText)
//        }
    }

    class ViewHolder(val binding: ItemAttendanceBinding, val context : Context) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Attendance, attendanceViewmodel: AttendanceViewmodel) {
            binding.attendanceCard.background = ContextCompat.getDrawable(context, item.bg)
            binding.subName.text = item.subject
            binding.went.text = item.attended.toString()
            binding.total.text = item.total.toString()
            binding.per.text = item.percentage.toString()+"%"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                binding.progressBar.setProgress(item.percentage.toInt(),true)
            }
            binding.increment.setOnClickListener {
                item.attended += 1
                item.total += 1
                item.percentage = (item.attended * 100) / item.total
                attendanceViewmodel.update(item)
                binding.went.text = item.attended.toString()
                binding.total.text = item.total.toString()
                binding.per.text = item.percentage.toString()+"%"
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.progressBar.setProgress(item.percentage.toInt(),true)
                }
            }
            binding.decrement.setOnClickListener {
                item.total += 1
                item.percentage = (item.attended * 100) / item.total
                attendanceViewmodel.update(item)
                binding.total.text = item.total.toString()
                binding.per.text = item.percentage.toString()+"%"
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.progressBar.setProgress(item.percentage.toInt(),true)
                }
            }
        }
    }

    class ListDiffCallbacks : DiffUtil.ItemCallback<Attendance>() {
        override fun areItemsTheSame(oldItem: Attendance, newItem: Attendance): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Attendance, newItem: Attendance): Boolean {
            return oldItem == newItem
        }
    }

    fun getList() = attendanceList

    fun removeitem(position: Int) {
        attendanceViewmodel.delete(attendanceList[position])
        notifyItemRemoved(position)
    }

    fun restoreItem(attendance: Attendance, position: Int) {
        attendanceList.add(position, attendance)
        notifyItemChanged(position)
        attendanceViewmodel.insert(attendance)
    }
}