package com.williams.platformscience.domain

import kotlinx.serialization.Serializable

@Serializable
data class ShipmentsDriversResponse(val shipments: List<String>, val drivers: List<String>)