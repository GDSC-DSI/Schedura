package net.code.gdsc_app.Attendance.Adapter

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import net.code.gdsc_app.Attendance.Database.Attendance
import net.code.gdsc_app.Attendance.Database.AttendanceViewmodel
import net.code.gdsc_app.R
import net.code.gdsc_app.databinding.ItemAttendanceBinding
import net.code.gdsc_app.utils.Snacker
import java.util.*
import kotlin.collections.ArrayList

class AttendanceAdapter(
    val attendanceViewmodel: AttendanceViewmodel,
    val parentView: View,
    private val context: Context,
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
        var subjectName = holder.binding.subName
        var popupBtn: ImageButton = holder.binding.popMenu


        if ((item.attended == 0L) || (item.total == 0L)) {
            item.percentage = 100
        } else {
            item.percentage = (item.attended * 100) / item.total
        }
        var perc = item.percentage
        holder.binding.attendanceCard.background = ContextCompat.getDrawable(context, item.bg)
        holder.binding.subName.text = item.subject
        holder.binding.went.text = item.attended.toString()
        holder.binding.total.text = item.total.toString()
        holder.binding.per.text = perc.toString() + "%"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.binding.progressBar.setProgress(perc.toInt(), true)
        }
        holder.binding.increment.setOnClickListener {
            item.attended += 1
            item.total += 1
            perc = (item.attended * 100) / item.total
            attendanceViewmodel.update(item)
            holder.binding.went.text = item.attended.toString()
            holder.binding.total.text = item.total.toString()
            holder.binding.per.text = perc.toString() + "%"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                holder.binding.progressBar.setProgress(perc.toInt(), true)
            }
        }
        holder.binding.decrement.setOnClickListener {
            if (holder.binding.total.text.toString() == "0") {
                Snacker(it, "Total classes cannot be zero")
            } else {
                item.total += 1
                perc = (item.attended * 100) / item.total
                attendanceViewmodel.update(item)
                holder.binding.total.text = item.total.toString()
                holder.binding.per.text = perc.toString() + "%"
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    holder.binding.progressBar.setProgress(perc.toInt(), true)
                }
            }
        }


        popupBtn.setOnClickListener { view ->
            val popup = PopupMenu(context, view)
            popup.menuInflater.inflate(R.menu.attendance_overflow_menu, popup.menu)
            popup.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {

                    R.id.delete -> {
                        AlertDialog.Builder(context)
                            .setTitle("Delete")
                            .setMessage("Are you sure you want to delete?")
                            .setPositiveButton("Yes") { dialog, _ ->
                                attendanceViewmodel.delete(item)
                                Toast.makeText(context, "Subject deleted", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            .setNegativeButton("No") { dialog, _ ->
                                Toast.makeText(context, "Subject not deleted", Toast.LENGTH_SHORT)
                                    .show()
                                attendanceViewmodel.update(item)
                            }
                            .setCancelable(false)
                            .show()

                    }

                    R.id.edit -> {
                        val builder = AlertDialog.Builder(context)
                        val inflater = (context as AppCompatActivity).layoutInflater
                        val view = inflater.inflate(R.layout.edit_attendance, null)
                        val attended_edit = view.findViewById<TextView>(R.id.edit_attended)
                        val total_classes = view.findViewById<TextView>(R.id.edit_total)
                        val subject_edit = view.findViewById<EditText>(R.id.edit_attendance_title)
                        val submit_btn = view.findViewById<Button>(R.id.btn_apply_attendance_edit)
                        val addAtten = view.findViewById<ImageButton>(R.id.add_atten)
                        val subAtten = view.findViewById<ImageButton>(R.id.sub_atten)
                        val addTotal = view.findViewById<ImageButton>(R.id.add_total)
                        val subTotal = view.findViewById<ImageButton>(R.id.sub_total)
                        subject_edit.setText(item.subject)
                        attended_edit.text = item.attended.toString()
                        total_classes.text = item.total.toString()
                        addAtten.setOnClickListener {
                            if (item.attended >= item.total) {
                                Snacker(
                                    it,
                                    "Attended Classes cannot be more than Total Classes"
                                ).error()
                            } else {
                                item.attended += 1
                                attended_edit.text = item.attended.toString()
                                item.percentage = (item.attended * 100) / item.total
                            }
                        }
                        subAtten.setOnClickListener {
                            if (item.attended <= 1) {
                                Snacker(it, "Attended Classes cannot be zero").error()
                            } else {
                                item.attended -= 1
                                attended_edit.text = item.attended.toString()
                                item.percentage = (item.attended * 100) / item.total
                            }
                        }
                        addTotal.setOnClickListener {
                            item.total += 1
                            total_classes.text = item.total.toString()
                            item.percentage = (item.attended * 100) / item.total
                        }
                        subTotal.setOnClickListener {
                            if (item.total <= 1) {
                                Snacker(it, "Total Classes cannot be zero").error()
                            } else {
                                item.total -= 1
                                total_classes.text = item.total.toString()
                                item.percentage = (item.attended * 100) / item.total
                            }

                        }
                        builder.setView(view)
                        val dialog = builder.create()
                        submit_btn.setOnClickListener {
                            if (item.total < item.attended) {
                                Snacker(
                                    it,
                                    "Total classes cannot be less than attended classes"
                                ).error()
                            } else {
                                item.subject = subject_edit.text.toString()
                                subjectName.text = item.subject
                                attendanceViewmodel.update(item)
                                notifyDataSetChanged()
                                dialog.dismiss()
                            }
                        }
                        Objects.requireNonNull(dialog.window)
                            ?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
                        Objects.requireNonNull(dialog.window)?.setBackgroundDrawable(
                            ColorDrawable(
                                Color.TRANSPARENT
                            )
                        )
                        dialog.show()
                    }
                }
                true
            }
            popup.show()
        }
    }

    class ViewHolder(val binding: ItemAttendanceBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {



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
}