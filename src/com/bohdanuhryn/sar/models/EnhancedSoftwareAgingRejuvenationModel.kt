package com.bohdanuhryn.sar.models

import com.bohdanuhryn.sar.methods.*
import com.bohdanuhryn.sar.models.base.Model

class EnhancedSoftwareAgingRejuvenationModel(
    val lamYA: Double,
    val lamAO: Double,
    val lamOAf: Double,
    val lamAR: Double,
    val lamRY: Double,
    val lamOR: Double,
    val lamAfY: Double,

    val initialY: Double,
    val initialA: Double,
    val initialO: Double,
    val initialAf: Double,
    val initialR: Double
) : Model() {

    override fun method(): Method {
        return RungeKuttaMethodSymets(
            arrayOf(
                arrayOf(lamYA * -1, 0.0, 0.0, lamAfY, lamRY),// S
                arrayOf(lamYA, (lamAO + lamAR) * -1, 0.0, 0.0, 0.0),// A
                arrayOf(0.0, lamAO, (lamOR + lamOAf) * -1, 0.0, 0.0),// O
                arrayOf(0.0, 0.0, lamOAf, lamAfY * -1, 0.0),// Af
                arrayOf(0.0, lamAR, lamOR, 0.0, lamRY * -1)// R
            )
        )
    }

    override fun initialProbabilities(): List<Double> {
        return listOf(
            initialY, // 0
            initialA, // 1
            initialO, // 2
            initialAf, // 3
            initialR // 4
        )
    }

    override fun namesOfStates(): List<String> {
        return listOf(
            "Y", // 0
            "A", // 1
            "O", // 2
            "Af", // 3
            "R" // 4
        )
    }

    override fun modelName(): String = "EnhancedSoftwareAgingRejuvenationModel"

}
