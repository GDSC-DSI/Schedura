package net.code.gdsc_app.Attendance.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.*
import net.code.gdsc_app.R
import net.code.gdsc_app.models.Post
import net.code.gdsc_app.ui.WeekDayFragment

class RecyclerViewAdapter : ListAdapter<Post, RecyclerViewAdapter.MyViewHolder>(Diff_Callback()) {


    class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val tvTime1:TextView =itemView.findViewById(R.id.tvTimeHour)
        val tvTime2:TextView =itemView.findViewById(R.id.tvTimeMinute)
        val tvSubject:TextView =itemView.findViewById(R.id.tvSubject)
    }

    class Diff_Callback:DiffUtil.ItemCallback<Post>(){
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
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
        val postItem= getItem(position)
//        holder.tvTime1.setText(postItem.name)
//        holder.tvTime2.setText(postItem.name)
//        holder.tvSubject.setText(postItem.body)

    }

}

