package com.williams.platformscience.domain

import kotlinx.serialization.Serializable

@Serializable
data class Driver(val name: String, var isSelectedForShipment: Boolean = false)