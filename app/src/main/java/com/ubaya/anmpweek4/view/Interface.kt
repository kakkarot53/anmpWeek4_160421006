package com.ubaya.anmpweek4.view

import android.view.View
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment

interface ButtonDetailClickListener{
    fun onButtonDetailClick(v: View){
        val action = StudentListFragmentDirections.actionStudentDetail(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }
}