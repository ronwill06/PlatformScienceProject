package com.williams.platformscience.ui

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.williams.platformscience.databinding.ViewHolderDriverBinding
import com.williams.platformscience.domain.Driver


class DriversAdapter(private val drivers: List<Driver>,
                     private val onDriverSelectedListener: OnDriverSelectedListener): RecyclerView.Adapter<DriverViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverViewHolder {
        return DriverViewHolder(ViewHolderDriverBinding.inflate(LayoutInflater.from(parent.context), parent, false), onDriverSelectedListener)
    }

    override fun onBindViewHolder(holder: DriverViewHolder, position: Int) {
        val driver = drivers[position]
        holder.bind(driver)
    }

    override fun getItemCount(): Int = drivers.size
}

class DriverViewHolder(private val binding: ViewHolderDriverBinding,
                       private val onDriverSelectedListener: OnDriverSelectedListener): RecyclerView.ViewHolder(binding.root) {

    fun bind(driver: Driver) {
        binding.driverNameTextView.text = driver.name
        binding.driverButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                driver.isSelectedForShipment = true
                onDriverSelectedListener.onDriverSelected(driver = driver)
            }
        }

        if (driver.isSelectedForShipment) {
            binding.driverNameTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            binding.driverButton.isEnabled = false
        }
    }
}