package com.bohdanuhryn.sar.models.base

import com.bohdanuhryn.sar.methods.Method
import com.bohdanuhryn.sar.utils.csv.saveToCsv

abstract class Model() {

    private var initialTime: Double = 0.0
    private var intervalTime: Double = 1.0
    private var maxTime: Double = 1000.0

    private var result: List<List<Double>> = listOf()

    abstract fun modelName(): String

    abstract fun namesOfStates(): List<String>

    abstract fun initialProbabilities(): List<Double>

    abstract fun method(): Method

    fun solve(initialTime: Double, intervalTime: Double, maxTime: Double): List<List<Double>> {
        this.initialTime = initialTime
        this.initialTime = intervalTime
        this.maxTime = maxTime
        val initialProbabilities = initialProbabilities()
        result = method().solve(initialTime, intervalTime, maxTime, initialProbabilities)
        return result
    }

    fun save() {
        if (result.isNotEmpty()) {
            val fullResults = fullResults(result)
            val columns = arrayListOf("timestamp").apply {
                addAll(namesOfStates())
                add("sum")
            }
            val filePath = "results/${modelName()}.csv"
            fullResults.saveToCsv(filePath = filePath, columns = columns)
        }
    }

    private fun fullResults(data: List<List<Double>>): List<List<Double>> {
        return data.mapIndexed { index, list -> arrayListOf<Double>().apply {
            add(initialTime + (index * intervalTime))
            addAll(list)
            add(list.sum())
        } }.toList()
    }

}