package com.bohdanuhryn.sar.models.base

import com.bohdanuhryn.sar.utils.csv.saveToCsv

class ModelTestResults(
    private val initialTime: Double,
    private val intervalTime: Double,
    private val maxTime: Double,
    private val result: List<List<Double>>,
    private val namesOfStates: List<String>,
    private val modelName: String
) {

    fun save() {
        if (result.isNotEmpty()) {
            val fullResults = fullResults(result)
            val columns = ArrayList<String>().apply {
                add("timestamp")
                add("lambdaYR")
                addAll(namesOfStates)
                add("sum")
            }
            val filePath = "results/$modelName-Test.csv"
            fullResults.saveToCsv(filePath = filePath, columns = columns)
        }
    }

    private fun fullResults(data: List<List<Double>>): List<List<Double>> {
        return data.mapIndexed { index, list -> arrayListOf<Double>().apply {
            add(initialTime + (index * intervalTime))
            addAll(list)
            add(list.drop(1).sum())
        } }.toList()
    }

}