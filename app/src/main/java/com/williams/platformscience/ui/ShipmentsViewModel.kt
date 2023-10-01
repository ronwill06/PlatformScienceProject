package com.williams.platformscience.ui

import androidx.lifecycle.ViewModel
import com.williams.platformscience.domain.Driver
import com.williams.platformscience.domain.Shipment
import com.williams.platformscience.domain.ShipmentsDriversResponse

class ShipmentsViewModel: ViewModel() {
    var shipments: List<Shipment> = emptyList()
    var drivers: List<Driver> = emptyList()
    var currentSelectedShipment: Shipment? = null

    fun saveShipmentsAndDrivers(response: ShipmentsDriversResponse) {
        shipments = response.shipments.map { Shipment(it) }
        drivers = response.drivers.map { Driver(it) }
    }
}