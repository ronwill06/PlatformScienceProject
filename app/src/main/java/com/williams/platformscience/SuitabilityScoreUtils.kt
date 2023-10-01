package com.williams.platformscience

import com.williams.platformscience.domain.Shipment
import com.williams.platformscience.extensions.countNumOfConsonants
import com.williams.platformscience.extensions.countNumOfVowels

object SuitabilityScoreUtils {
    fun calculateScore(shipment: Shipment): Double {
        val vowelCountInStreetName = shipment.destinationStreetName.countNumOfVowels()
        val vowelCountInDriverName = shipment.driver?.name?.countNumOfVowels() ?: 0
        val consonantCountInDriverName = shipment.driver?.name?.countNumOfConsonants() ?: 0
        var baseSusScore = if (shipment.isStreetNameLengthEven) {
           vowelCountInDriverName * 1.5
        } else {
           consonantCountInDriverName * 1.0
        }

        if (shipment.isStreetNameAndDriverNameCommon()) {
            baseSusScore *= (baseSusScore * 0.50)
        }

        return baseSusScore
    }

}