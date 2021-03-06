package com.bohdanuhryn.sar.models

import com.bohdanuhryn.sar.methods.*
import com.bohdanuhryn.sar.models.base.Model
import kotlin.math.exp

class MobileDeviceWithRejuvenationModel(
    val lamSA: Double,
    val lamAS: Double,
    val lamYO: Double,
    val lamORe: Double,
    val lamReY: Double,
    val lamYR: Double,
    val lamRY: Double,
    val lamSpLp: Double,
    val lamLpSp: Double,
    val lamLpOp: Double,

    val initialSYSp: Double,
    val initialAYSp: Double,
    val initialSOSp: Double,
    val initialAOSp: Double,
    val initialRASp: Double,
    val initialRSSp: Double,
    val initialAReSp: Double,
    val initialSReSp: Double,

    val initialSYLp: Double,
    val initialAYLp: Double,
    val initialSOLp: Double,
    val initialAOLp: Double,
    val initialRALp: Double,
    val initialRSLp: Double,
    val initialAReLp: Double,
    val initialSReLp: Double,
    val initialOp: Double
) : Model() {

    override fun method(): Method {
        fun balance(p: List<Double>) = (1.0f - (p[0] + p[1] + p[2] + p[3] + p[4] + p[5] + p[6] + p[7] + p[8] + p[9]
                + p[10] + p[11] + p[12] + p[13] + p[14] + p[15]))
        /*//return RungeKuttaMethod1(
        return RungeKuttaMethod2(
        //return RungeKuttaMethodFlanagan(
            equations = listOf(
                { x, p: List<Double> -> -(p[0] * (lamSA + lamYO + lamYR + lamSpLp)) + p[7] * lamReY + p[1] * lamAS + p[5] * lamRY + p[8] * lamLpSp },// SYSp
                { x, p: List<Double> -> -(p[1] * (lamAS + lamYO + lamSpLp)) + p[0] * lamSA + p[6] * lamReY + p[4] * lamRY + p[9] * lamLpSp },// AYSp
                { x, p: List<Double> -> -(p[2] * (lamORe + lamSA + lamSpLp)) + p[0] * lamYO + p[3] * lamAS + p[10] * lamLpSp },// SOSp
                { x, p: List<Double> -> -(p[3] * (lamORe + lamAS + lamSpLp)) + p[2] * lamSA + p[1] * lamYO + p[11] * lamLpSp },// AOSp
                { x, p: List<Double> -> -(p[4] * (lamRY + lamAS + lamSpLp)) + p[5] * lamSA + p[12] * lamLpSp },// RASp
                { x, p: List<Double> -> -(p[5] * (lamRY + lamSA + lamSpLp)) + p[4] * lamAS + p[0] * lamYR + p[13] * lamLpSp },// RSSp
                { x, p: List<Double> -> -(p[6] * (lamReY + lamAS + lamSpLp)) + p[3] * lamORe + p[7] * lamSA + p[14] * lamLpSp },// AReSp
                { x, p: List<Double> -> -(p[7] * (lamReY + lamSA + lamSpLp)) + p[2] * lamORe + p[6] * lamAS + p[15] * lamLpSp },// SReSp

                { x, p: List<Double> -> -(p[8] * (lamSA + lamYO + lamLpSp + lamLpOp)) + p[15] * lamReY + p[9] * lamAS + p[13] * lamRY + p[0] * lamSpLp },// SYLp
                { x, p: List<Double> -> -(p[9] * (lamAS + lamYO + lamLpSp + lamLpOp)) + p[8] * lamSA + p[14] * lamReY + p[12] * lamRY + p[1] * lamSpLp },// AYLp
                { x, p: List<Double> -> -(p[10] * (lamORe + lamSA + lamLpSp + lamLpOp)) + p[8] * lamYO + p[11] * lamAS + p[2] * lamSpLp },// SOLp
                { x, p: List<Double> -> -(p[11] * (lamORe + lamAS + lamLpSp + lamLpOp)) + p[10] * lamSA + p[9] * lamYO + p[3] * lamSpLp },// AOLp
                { x, p: List<Double> -> -(p[12] * (lamRY + lamAS + lamLpSp + lamLpOp)) + p[13] * lamSA + p[4] * lamSpLp },// RALp
                { x, p: List<Double> -> -(p[13] * (lamRY + lamSA + lamLpSp + lamLpOp)) + p[12] * lamAS + p[5] * lamSpLp },// RSLp
                { x, p: List<Double> -> -(p[14] * (lamReY + lamAS + lamLpSp + lamLpOp)) + p[11] * lamORe + p[15] * lamSA + p[6] * lamSpLp },// AReLp
                { x, p: List<Double> -> -(p[15] * (lamReY + lamSA + lamLpSp + lamLpOp)) + p[10] * lamORe + p[14] * lamAS + p[7] * lamSpLp },// SReLp

                { x, p: List<Double> -> p[14] * lamLpOp + p[11] * lamLpOp + p[9] * lamLpOp + p[8] * lamLpOp + p[10] * lamLpOp + p[15] * lamLpOp + p[13] * lamLpOp + p[12] * lamLpOp }// Op
            )
        )*/
        return RungeKuttaMethodSymets(
            arrayOf(
                arrayOf((lamSA + lamYO + lamYR + lamSpLp) * -1, lamAS, 0.0, 0.0, 0.0, lamRY, 0.0, lamReY, lamLpSp, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),// SYSp
                arrayOf(lamSA, (lamAS + lamYO + lamSpLp) * -1, 0.0, 0.0, lamRY, 0.0, lamReY, 0.0, 0.0, lamLpSp, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),// AYSp
                arrayOf(lamYO, 0.0, (lamORe + lamSA + lamSpLp) * -1, lamAS, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, lamLpSp, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0),// SOSp
                arrayOf(0.0, lamYO, lamSA, (lamORe + lamAS + lamSpLp) * -1, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, lamLpSp, 0.0, 0.0, 0.0, 0.0, 0.0),// AOSp
                arrayOf(0.0, 0.0, 0.0, 0.0, (lamRY + lamAS + lamSpLp) * -1, lamSA, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, lamLpSp, 0.0, 0.0, 0.0, 0.0),// RASp
                arrayOf(lamYR, 0.0, 0.0, 0.0, lamAS, (lamRY + lamSA + lamSpLp) * -1, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, lamLpSp, 0.0, 0.0, 0.0),// RSSp
                arrayOf(0.0, 0.0, 0.0, lamORe, 0.0, 0.0, (lamReY + lamAS + lamSpLp) * -1, lamSA, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, lamLpSp, 0.0, 0.0),// AReSp
                arrayOf(0.0, 0.0, lamORe, 0.0, 0.0, 0.0, lamAS, (lamReY + lamSA + lamSpLp) * -1, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, lamLpSp, 0.0),// SReSp

                arrayOf(lamSpLp, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, (lamSA + lamYO + lamLpSp + lamLpOp) * -1, lamAS, 0.0, 0.0, 0.0, lamRY, 0.0, lamReY, 0.0),// SYLp
                arrayOf(0.0, lamSpLp, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, lamSA, (lamAS + lamYO + lamLpSp + lamLpOp) * -1, 0.0, 0.0, lamRY, 0.0, lamReY, 0.0, 0.0),// AYLp
                arrayOf(0.0, 0.0, lamSpLp, 0.0, 0.0, 0.0, 0.0, 0.0, lamYO, 0.0, (lamORe + lamSA + lamLpSp + lamLpOp) * -1, lamAS, 0.0, 0.0, 0.0, 0.0, 0.0),// SOLp
                arrayOf(0.0, 0.0, 0.0, lamSpLp, 0.0, 0.0, 0.0, 0.0, 0.0, lamYO, lamSA, (lamORe + lamAS + lamLpSp + lamLpOp) * -1, 0.0, 0.0, 0.0, 0.0, 0.0),// AOLp
                arrayOf(0.0, 0.0, 0.0, 0.0, lamSpLp, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, (lamRY + lamAS + lamLpSp + lamLpOp) * -1, lamSA, 0.0, 0.0, 0.0),// RALp
                arrayOf(0.0, 0.0, 0.0, 0.0, 0.0, lamSpLp, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, lamAS, (lamRY + lamSA + lamLpSp + lamLpOp) * -1, 0.0, 0.0, 0.0),// RSLp
                arrayOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, lamSpLp, 0.0, 0.0, 0.0, 0.0, lamORe, 0.0, 0.0, (lamReY + lamAS + lamLpSp + lamLpOp) * -1, lamSA, 0.0),// AReLp
                arrayOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, lamSpLp, 0.0, 0.0, lamORe, 0.0, 0.0, 0.0, lamAS, (lamReY + lamSA + lamLpSp + lamLpOp) * -1, 0.0),// SReLp

                arrayOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, lamLpOp, lamLpOp, lamLpOp, lamLpOp, lamLpOp, lamLpOp, lamLpOp, lamLpOp, 0.0)// Op
            )
        )
    }

    override fun initialProbabilities(): List<Double> {
        return listOf(
            initialSYSp, // 0
            initialAYSp, // 1
            initialSOSp, // 2
            initialAOSp, // 3
            initialRASp, // 4
            initialRSSp, // 5
            initialAReSp, // 6
            initialSReSp, // 7
            initialSYLp, // 8
            initialAYLp, // 9
            initialSOLp, // 10
            initialAOLp, // 11
            initialRALp, // 12
            initialRSLp, // 13
            initialAReLp, // 14
            initialSReLp, // 15
            initialOp // 16
        )
    }

    override fun namesOfStates(): List<String> {
        return listOf(
            "SYSp", // 0
            "AYSp", // 1
            "SOSp", // 2
            "AOSp", // 3
            "RASp", // 4
            "RSSp", // 5
            "AReSp", // 6
            "SReSp", // 7
            "SYLp", // 8
            "AYLp", // 9
            "SOLp", // 10
            "AOLp", // 11
            "RALp", // 12
            "RSLp", // 13
            "AReLp", // 14
            "SReLp", // 15
            "Op" // 16
        )
    }

    override fun modelName(): String = "MobileDeviceWithRejuvenationModel"

}