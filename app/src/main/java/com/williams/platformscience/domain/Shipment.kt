package com.williams.platformscience.domain

import com.williams.platformscience.extensions.countNumOfConsonants
import com.williams.platformscience.extensions.countNumOfVowels
import kotlinx.serialization.Serializable

@Serializable
class Shipment(val destinationStreetName: String, var driver: Driver? = null) {

    val isStreetNameLengthEven: Boolean
        get() = (destinationStreetName.length / 2) == 0

    val isDriverNameLengthEven: Boolean
        get() = (driver?.name?.length?.div(2)) == 0

    fun isStreetNameAndDriverNameCommon(): Boolean {
        return (destinationStreetName.length > 1 && (driver?.name?.length ?: 0) > 1) &&
                isStreetNameLengthEven && isDriverNameLengthEven ||
                destinationStreetName.countNumOfVowels() == driver?.name?.countNumOfVowels() ||
                destinationStreetName.countNumOfConsonants() == driver?.name?.countNumOfConsonants()
    }
}