package net.code.gdsc_app.Attendance.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.*
import com.google.gson.JsonArray
import net.code.gdsc_app.R
import net.code.gdsc_app.models.Subject
import net.code.gdsc_app.utils.Constants

class RecyclerViewAdapter : ListAdapter<Subject, RecyclerViewAdapter.MyViewHolder>(Diff_Callback()) {


    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val tvTime1:TextView =itemView.findViewById(R.id.tvTimeHour)
        val tvTime2:TextView =itemView.findViewById(R.id.tvTimeMinute)
        val tvSubject:TextView =itemView.findViewById(R.id.tvSubject)
    }

    class Diff_Callback:DiffUtil.ItemCallback<Subject>(){
        override fun areItemsTheSame(oldItem: Subject, newItem: Subject): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Subject, newItem: Subject): Boolean {
            return oldItem==newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_view_item,
            parent,
            false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val subject = getItem(position)
        holder.tvTime1.text = Constants.timingsMap[position]?.get(0)
        holder.tvTime2.text = Constants.timingsMap[position]?.get(1)
        holder.tvSubject.text = subject

    }

}

