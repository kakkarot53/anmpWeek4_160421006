package com.ubaya.anmpweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.ubaya.anmpweek4.R
import com.ubaya.anmpweek4.databinding.FragmentStudentDetailBinding
import com.ubaya.anmpweek4.viewmodel.DetailViewModel


class StudentDetailFragment : Fragment() {
    private lateinit var binding: FragmentStudentDetailBinding
    private lateinit var detailViewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailViewModel.fetch()

        detailViewModel.studentLD.observe(viewLifecycleOwner) { student ->
            binding.apply {
                txtID.setText(student.id)
                txtName.setText(student.name)
                txtBoD.setText(student.bod)
                txtPhone.setText(student.phone)
            }
        }
    }
}