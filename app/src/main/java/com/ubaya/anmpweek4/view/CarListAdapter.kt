package com.ubaya.anmpweek4.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.anmpweek4.databinding.CarListItemBinding
import com.ubaya.anmpweek4.model.Cars

class CarListAdapter(val CarList: ArrayList<Cars>) :
    RecyclerView.Adapter<CarListAdapter.CarViewHolder>() {
    class CarViewHolder(var binding: CarListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = CarListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CarViewHolder(binding)

    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.binding.txtCarName.text = "Model: " + CarList[position].model + " year " + CarList[position].year
        holder.binding.txtCarBrand.text = "Color: " + CarList[position].color
        holder.binding.txtCarColor.text = "Price: $" + CarList[position].price
    }

    override fun getItemCount(): Int {
        return CarList.size
    }

    fun updateCarList(newCarList: ArrayList<Cars>) {
        CarList.clear()
        CarList.addAll(newCarList)
        notifyDataSetChanged()
    }

}