package com.ubaya.anmpweek4.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.ubaya.anmpweek4.R
import com.ubaya.anmpweek4.databinding.FragmentStudentDetailBinding
import com.ubaya.anmpweek4.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

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

        val id = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentID

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        detailViewModel.fetch(id)

        detailViewModel.studentLD.observe(viewLifecycleOwner) { student ->
            binding.apply {
                txtID.setText(student.id)
                txtName.setText(student.name)
                txtBoD.setText(student.bod)
                txtPhone.setText(student.phone)

                val picasso = Picasso.Builder(imageView2.context)
                picasso.build().load(student.photoUrl).into(imageView2)
            }
        }
        observeViewModel()
    }

    fun observeViewModel() {
        detailViewModel.studentLD.observe(viewLifecycleOwner, Observer {
            var student = it

            binding.btnUpdate?.setOnClickListener {Observable.timer(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("Messages", "Five seconds")
                    MainActivity.showNotification(student.name.toString(), "A new notification created", R.drawable.baseline_person_24)
                }
            }
        })
    }
}
