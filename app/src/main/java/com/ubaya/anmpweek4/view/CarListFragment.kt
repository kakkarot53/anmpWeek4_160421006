package com.ubaya.anmpweek4.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ubaya.anmpweek4.databinding.FragmentCarListBinding
import com.ubaya.anmpweek4.viewmodel.ListViewModel


class CarListFragment : Fragment() {
    private lateinit var viewModel: ListViewModel
    private lateinit var binding: FragmentCarListBinding
    private val carListAdpater  = CarListAdapter(arrayListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCarListBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.carRefresh()

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = carListAdpater

        observeViewModel()

        binding.refreshLayout.setOnRefreshListener {
            binding.recView.visibility = View.GONE
            binding.txtError.visibility = View.GONE
            binding.progressLoad.visibility = View.VISIBLE
            viewModel.carRefresh()
            binding.refreshLayout.isRefreshing = false
        }

    }

    fun observeViewModel(){
        viewModel.carsLD.observe(viewLifecycleOwner, Observer {
            carListAdpater.updateCarList(it)
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer{
            if (it == true){
                binding.recView.visibility = View.GONE
                binding.progressLoad.visibility = View.VISIBLE
            } else {
                binding.recView.visibility = View.VISIBLE
                binding.progressLoad.visibility = View.GONE
            }
        })

        viewModel.carLoadErrorLD.observe(viewLifecycleOwner, Observer{
            if (it == true){
                binding.txtError.visibility = View.VISIBLE
            } else {
                binding.txtError.visibility = View.GONE
            }
        })
    }

}