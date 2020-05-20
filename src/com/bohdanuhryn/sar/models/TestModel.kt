package com.bohdanuhryn.sar.models

import com.bohdanuhryn.sar.methods.Method
import com.bohdanuhryn.sar.methods.RungeKuttaMethod1
import com.bohdanuhryn.sar.models.base.Model

class TestModel(
    val hw1: Double,
    val hw2: Double,
    val hw3: Double,
    val hw4: Double,
    val hw5: Double,
    val sw2: Double,
    val sw3: Double,

    val initial1: Double,
    val initial2: Double,
    val initial3: Double,
    val initial4: Double,
    val initial5: Double
) : Model() {

    override fun method(): Method {
        return RungeKuttaMethod1(
            equations = listOf(
                { x, p: List<Double> -> -(hw1 + hw2 + sw2 + hw3 + sw3 + hw4 + hw5) * p[0] },// SY
                { x, p: List<Double> -> (hw1) * p[0] - (hw2 + sw2 + hw3 + sw3 + hw4 + hw5) * p[1] },// AY
                { x, p: List<Double> -> (hw2 + sw2) * p[0] - (hw1 + hw3 + sw3 + hw4 + hw5) * p[2] },// SO
                { x, p: List<Double> -> (hw3 + sw3) * p[0] - (hw1 + hw2 + sw2 + hw4 + hw5) * p[3] },// AO
                { x, p: List<Double> -> (hw2 + sw2) * p[1] + (hw1) * p[2] - (hw3 + sw3 + hw4 + hw5) * p[4] }
            )
        )
    }

    override fun initialProbabilities(): List<Double> {
        return listOf(
            initial1, // 0
            initial2, // 1
            initial3, // 2
            initial4, // 3
            initial5
        )
    }

    override fun namesOfStates(): List<String> {
        return listOf(
            "s1", // 0
            "s2", // 1
            "s3", // 2
            "s4", // 3
            "s5" // 4
        )
    }

    override fun modelName(): String = "TestModel"

}