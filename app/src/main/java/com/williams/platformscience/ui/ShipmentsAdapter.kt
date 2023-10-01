package com.williams.platformscience.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.williams.platformscience.R
import com.williams.platformscience.SuitabilityScoreUtils
import com.williams.platformscience.databinding.ViewHolderShipmentBinding
import com.williams.platformscience.domain.Driver
import com.williams.platformscience.domain.Shipment

interface OnDriverSelectedListener {
    fun onOpenDriverPickerForShipment(shipment: Shipment)
    fun onDriverSelected(driver: Driver)
}

class ShipmentsAdapter(private val shipments: List<Shipment>,
                       private val onDriverSelectedListener: OnDriverSelectedListener):
    RecyclerView.Adapter<ShipmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShipmentViewHolder {
        return ShipmentViewHolder(ViewHolderShipmentBinding.inflate(LayoutInflater.from(parent.context), parent, false), onDriverSelectedListener)
    }

    override fun onBindViewHolder(holder: ShipmentViewHolder, position: Int) {
        holder.bind(shipments[position])
    }

    override fun getItemCount(): Int = shipments.size

    fun findIndexOfShipmentWithAddress(streetName: String): Int {
        return shipments.indexOfFirst { it.destinationStreetName == streetName }
    }

    fun updateShipmentAtIndexWithDriver(index: Int, driver: Driver): Driver {
        val shipment = shipments[index]
        shipment.driver = driver
        return driver
    }
}

class ShipmentViewHolder(private val binding: ViewHolderShipmentBinding,
                         private val onDriverSelectedListener: OnDriverSelectedListener): ViewHolder(binding.root) {

    fun bind(shipment: Shipment) {
        binding.shipmentAddressTextView.text = shipment.destinationStreetName
        if (shipment.driver != null) {
            binding.driverNameTextView.text = shipment.driver?.name
        }
        val score = SuitabilityScoreUtils.calculateScore(shipment)
        binding.scoreTextView.text =
            binding.root.context.resources.getString(R.string.sustainability_score, score)
        binding.selectDriverButton.setOnClickListener {
            onDriverSelectedListener.onOpenDriverPickerForShipment(shipment)
        }
    }
}