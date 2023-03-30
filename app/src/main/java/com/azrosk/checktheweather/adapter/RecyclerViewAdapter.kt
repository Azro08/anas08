package com.azrosk.checktheweather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azrosk.checktheweather.databinding.StatusItemViewBinding
import com.azrosk.checktheweather.models.WeatherStatus

class RecyclerViewAdapter (private val weatherStatus: ArrayList<WeatherStatus>) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    class MyViewHolder( private var binding: StatusItemViewBinding) : RecyclerView.ViewHolder(binding.root)   {
        private var weatherStatus : WeatherStatus?=null
        fun bind(stat: WeatherStatus){
            binding.statusIcon.setBackgroundResource(stat.icon)
            binding.tvStatusName.text = stat.name
            binding.tvStatus.text = stat.status
            weatherStatus = stat
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =  StatusItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(weatherStatus[position])
    }

    override fun getItemCount(): Int {
        return weatherStatus.size
    }
}