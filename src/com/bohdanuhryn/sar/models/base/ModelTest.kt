package com.bohdanuhryn.sar.models.base

class ModelTest(
    private val initialTime: Double,
    private val maxTime: Double,
    private val intervalTime: Double
) {

    fun test(
        model: (lambdaYR: Double, lambdaYO: Double) -> Model
    ): ModelTestResults {
        val results: ArrayList<List<Double>> = ArrayList()

        val modelName = model(0.0, 0.0).modelName()
        val namesOfStates = model(0.0, 0.0).namesOfStates()

        var t = initialTime + intervalTime
        while (t < maxTime) {
            println("$modelName test iteration #${(t / intervalTime) + 1}/${(maxTime - initialTime) / intervalTime}")

            val lambdaYR = 1.0 / t
            val a = 10000.0
            val b = 220.0
            val lambdaYO = 1.0 / (a * lambdaYR + b)
            val result: ArrayList<Double> = ArrayList()
            result.add(lambdaYR)
            result.addAll(model(lambdaYR, lambdaYO).solve(initialTime, intervalTime, t).last())
            results.add(result)

            t += intervalTime
        }
        return ModelTestResults(
            initialTime = initialTime,
            intervalTime = intervalTime,
            maxTime = maxTime,
            modelName = modelName,
            namesOfStates = namesOfStates,
            result = results
        )
    }

}