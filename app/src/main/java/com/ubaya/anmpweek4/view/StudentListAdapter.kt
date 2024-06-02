package com.ubaya.anmpweek4.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.anmpweek4.R
import com.ubaya.anmpweek4.databinding.StudentListItemBinding
import com.ubaya.anmpweek4.model.Student


    class StudentListAdapter(val studentList: ArrayList<Student>) :
        RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>()
        , ButtonDetailClickListener
    {
        class StudentViewHolder(var view: StudentListItemBinding) :
            RecyclerView.ViewHolder(view.root) {
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = DataBindingUtil.inflate<StudentListItemBinding>(inflater, R.layout.student_list_item, parent, false)
            return StudentViewHolder(view)
        }

        override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
            holder.view.student = studentList[position]
            holder.view.listener = this

//            holder.view.txtID.text = studentList[position].id
//            holder.view.txtName.text = studentList[position].name
//
//            holder.view.btnDetail.setOnClickListener {
//                val action = StudentListFragmentDirections.actionStudentDetail(studentList[position].id.toString())
//                Navigation.findNavController(it).navigate(action)
//            }
//            val picasso = Picasso.Builder(holder.itemView.context)
//            picasso.listener { picasso, uri, exception -> exception.printStackTrace()
//            }
//            picasso.build().load(studentList[position].photoUrl).into(holder.view.imgStudent, object:
//                Callback {
//                override fun onSuccess() {
//                    holder.view.progressImg.visibility = View.INVISIBLE
//                    holder.view.imgStudent.visibility = View.VISIBLE
//                }
//                override fun onError(e: Exception?) {
//                    Log.e("picasso_error", e.toString())
//                }
//                })
        }

        override fun getItemCount(): Int {
            return studentList.size
        }

        fun updateStudentList(newStudentList: ArrayList<Student>) {
            studentList.clear()
            studentList.addAll(newStudentList)
            notifyDataSetChanged()
        }


    }
