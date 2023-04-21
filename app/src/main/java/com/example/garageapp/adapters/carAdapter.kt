package com.example.garageapp.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.garageapp.databinding.CarItemBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.garageapp.Utils.BitMapConverter
import com.example.garageapp.models.carInfo

class carAdapter ( private val onCarClicked: (carInfo) -> Unit, private val onAddImage: (carInfo) ->Unit) :
    ListAdapter<carInfo, carAdapter.ViewHolder>(ComparatorDiffUtil()) {

    inner class  ViewHolder(val binding:CarItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(car: carInfo) {
                with(car){
                    binding.tvCarMake.text= this.carMaker
                    binding.tvCarModel.text= this.carModel
                    if(car.carImage!=null) {
                        val bitmap= BitMapConverter.converterStringToBitmap(this.carImage!!)
                        binding.CarImage.setImageBitmap(bitmap)
                    }
                    binding.BtnDelete.setOnClickListener {
                        onCarClicked(this)
                    }
                    binding.BtnAddImg.setOnClickListener {
                        onAddImage(this)
                    }
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CarItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val car= getItem(position)
        car.let {
            holder.bind(it)
        }

    }

    class ComparatorDiffUtil : DiffUtil.ItemCallback<carInfo>() {
        override fun areItemsTheSame(oldItem: carInfo, newItem: carInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: carInfo, newItem: carInfo): Boolean {
            return oldItem == newItem
        }
    }
}