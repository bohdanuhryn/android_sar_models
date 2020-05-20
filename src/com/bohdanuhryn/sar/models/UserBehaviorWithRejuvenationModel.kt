package com.bohdanuhryn.sar.models

import com.bohdanuhryn.sar.methods.RungeKuttaMethodFlanagan
import com.bohdanuhryn.sar.methods.Method
import com.bohdanuhryn.sar.models.base.Model

class UserBehaviorWithRejuvenationModel(
    val lamSA: (x: Double) -> Double,
    val lamAS: (x: Double) -> Double,
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
        //return RungeKuttaMethod1(
        //return RungeKuttaMethod2(
        return RungeKuttaMethodFlanagan(
            equations = listOf(
                { x, p: List<Double> -> -(p[0] * (lamSA(x) + lamYO + lamYR)) + (p[7] * lamReY) + (p[1] * lamAS(x)) + (p[5] * lamRY) },// SY
                { x, p: List<Double> -> -(p[1] * (lamAS(x) + lamYO)) + (p[0] * lamSA(x)) + (p[6] * lamReY) + (p[4] * lamRY) },// AY
                { x, p: List<Double> -> -(p[2] * (lamORe + lamSA(x))) + (p[0] * lamYO) + (p[3] * lamAS(x)) },// SO
                { x, p: List<Double> -> -(p[3] * (lamORe + lamAS(x))) + (p[2] * lamSA(x)) + (p[1] * lamYO) },// AO
                { x, p: List<Double> -> -(p[4] * (lamRY + lamAS(x))) + (p[5] * lamSA(x)) },// RA
                { x, p: List<Double> -> -(p[5] * (lamRY + lamSA(x))) + (p[4] * lamAS(x)) + (p[0] * lamYR) },// RS
                { x, p: List<Double> -> -(p[6] * (lamReY + lamAS(x))) + (p[3] * lamORe) + (p[7] * lamSA(x)) },// ARe
                { x, p: List<Double> -> -(p[7] * (lamReY + lamSA(x))) + (p[2] * lamORe) + (p[6] * lamAS(x)) }// SRe
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