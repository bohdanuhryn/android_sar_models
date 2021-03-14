package com.bohdanuhryn.sar.models

import com.bohdanuhryn.sar.methods.*
import com.bohdanuhryn.sar.models.base.Model

class UserBehaviorWithRejuvenationModel(
    val lamSA: Double,
    val lamAS: Double,
    val lamYO: Double,
    val lamORe: Double,
    val lamReY: Double,
    val lamYR: Double,
    val lamRY: Double,

    val initialSY: Double,
    val initialAY: Double,
    val initialSO: Double,
    val initialAO: Double,
    val initialRA: Double,
    val initialRS: Double,
    val initialARe: Double,
    val initialSRe: Double
) : Model() {

    override fun method(): Method {
        // (1 - (p[0] + p[1] + p[2] + p[3] + p[4] + p[5] + p[6]))
        fun balance(p: List<Double>) = (1.0f - (p[0] + p[1] + p[2] + p[3] + p[4] + p[5] + p[6]))
        //return RungeKuttaMethod1(
        //return RungeKuttaMethod2(
        /*return RungeKuttaMethodFlanagan(
            equations = listOf(
                { x, p: List<Double> -> -(p[0] * (lamSA + lamYO + lamYR)) + (p[7] * lamReY) + (p[1] * lamAS) + (p[5] * lamRY) },// SY
                { x, p: List<Double> -> -(p[1] * (lamAS + lamYO)) + (p[0] * lamSA) + (p[6] * lamReY) + (p[4] * lamRY) },// AY
                { x, p: List<Double> -> -(p[2] * (lamORe + lamSA)) + (p[0] * lamYO) + (p[3] * lamAS) },// SO
                { x, p: List<Double> -> -(p[3] * (lamORe + lamAS)) + (p[2] * lamSA) + (p[1] * lamYO) },// AO
                { x, p: List<Double> -> -(p[4] * (lamRY + lamAS)) + (p[5] * lamSA) },// RA
                { x, p: List<Double> -> -(p[5] * (lamRY + lamSA)) + (p[4] * lamAS) + (p[0] * lamYR) },// RS
                { x, p: List<Double> -> -(p[6] * (lamReY + lamAS)) + (p[3] * lamORe) + (p[7] * lamSA) },// ARe
                { x, p: List<Double> -> -(p[7] * (lamReY + lamSA)) + (p[2] * lamORe) + (p[6] * lamAS) }// SRe
            )
        )*/
        return RungeKuttaMethodSymets(
            arrayOf(
                arrayOf((lamSA + lamYO + lamYR) * -1, lamAS, 0.0, 0.0, 0.0, lamRY, 0.0, lamReY),// SY
                arrayOf(lamSA, (lamAS + lamYO) * -1, 0.0, 0.0, lamRY, 0.0, lamReY, 0.0),// AY
                arrayOf(lamYO, 0.0, (lamORe + lamSA) * -1, lamAS, 0.0, 0.0, 0.0, 0.0),// SO
                arrayOf(0.0, lamYO, lamSA, (lamORe + lamAS) * -1, 0.0, 0.0, 0.0, 0.0),// AO
                arrayOf(0.0, 0.0, 0.0, 0.0, (lamRY + lamAS) * -1, lamSA, 0.0, 0.0),// RA
                arrayOf(lamYR, 0.0, 0.0, 0.0, lamAS, (lamRY + lamSA) * -1, 0.0, 0.0),// RS
                arrayOf(0.0, 0.0, 0.0, lamORe, 0.0, 0.0, (lamReY + lamAS) * -1, lamSA),// ARe
                arrayOf(0.0, 0.0, lamORe, 0.0, 0.0, 0.0, lamAS, (lamReY + lamSA) * -1)// SRe
            )
        )
    }

    override fun initialProbabilities(): List<Double> {
        return listOf(
            initialSY, // 0
            initialAY, // 1
            initialSO, // 2
            initialAO, // 3
            initialRA, // 4
            initialRS, // 5
            initialARe, // 6
            initialSRe // 7
        )
    }

    override fun namesOfStates(): List<String> {
        return listOf(
            "SY", // 0
            "AY", // 1
            "SO", // 2
            "AO", // 3
            "RA", // 4
            "RS", // 5
            "ARe", // 6
            "SRe" // 7
        )
    }

    override fun modelName(): String = "UserBehaviorWithRejuvenationModel"

}