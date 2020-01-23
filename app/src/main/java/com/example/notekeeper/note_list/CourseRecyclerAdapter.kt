package com.example.notekeeper.note_list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notekeeper.DataManager
import com.example.notekeeper.R
import com.google.android.material.snackbar.Snackbar


class CourseRecyclerAdapter(private val context: Context) :
    RecyclerView.Adapter<CourseRecyclerAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    private val coursesList = DataManager.courses.values.toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.item_course_list, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return coursesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val course = coursesList[position]
        holder.courseTitle.text = course.title
        holder.coursePosition = position
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val courseTitle = itemView.findViewById<TextView>(R.id.courseTitle)
        var coursePosition = 0

        init {
            itemView.setOnClickListener {
                Snackbar.make(
                    it,
                    coursesList[coursePosition].title,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
}