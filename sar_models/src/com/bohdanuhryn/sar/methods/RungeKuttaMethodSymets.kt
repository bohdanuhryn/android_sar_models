package com.bohdanuhryn.sar.methods


class RungeKuttaMethodSymets(
    val coefficients: Array<Array<Double>>
) : Method {

    override fun solve(initialX: Double, deltaX: Double, maxX: Double, initialY: List<Double>): List<List<Double>> {
        val result: ArrayList<List<Double>> = ArrayList()

        val maxTime: Int = ((maxX - initialX) / deltaX).toInt()
        val equationCount = coefficients.size
        val step = deltaX

        val initialValues = initialY.toDoubleArray()

        val k0 = DoubleArray(maxTime * equationCount)
        val k1 = DoubleArray(maxTime * equationCount)
        val k1_help = DoubleArray(equationCount)
        val k2 = DoubleArray(maxTime * equationCount)
        val k2_help = DoubleArray(equationCount)
        val k3 = DoubleArray(maxTime * equationCount)
        val k3_help = DoubleArray(equationCount)
        val res = DoubleArray(maxTime * equationCount)

        var k = 0
        while (k < maxTime) {
            val resultTmpl: ArrayList<Double> = ArrayList()
            for (i in 0 until equationCount) {
                k0[i + k * equationCount] = func(initialValues, i, equationCount, coefficients) * step
            }
            for (i in 0 until equationCount) {
                k1_help[i] = initialValues[i] + k0[i + k * equationCount] / 2
            }
            for (i in 0 until equationCount) {
                k1[i + k * equationCount] = func(k1_help, i, equationCount, coefficients) * step
            }
            for (i in 0 until equationCount) {
                k2_help[i] = initialValues[i] + k1[i + k * equationCount] / 2
            }
            for (i in 0 until equationCount) {
                k2[i + k * equationCount] = func(k2_help, i, equationCount, coefficients) * step
            }
            for (i in 0 until equationCount) {
                k3_help[i] = initialValues[i] + k2[i + k * equationCount]
            }
            for (i in 0 until equationCount) {
                k3[i + k * equationCount] = func(k3_help, i, equationCount, coefficients) * step
            }
            for (i in 0 until equationCount) {
                res[i + k * equationCount] =
                    initialValues[i] + 1.0 / 6.0 * (k0[i + k * equationCount] + 2 * k1[i + k * equationCount] + 2 * k2[i + k * equationCount] + k3[i + k * equationCount])
            }
            for (i in 0 until equationCount) {
                initialValues[i] = res[i + k * equationCount]
                resultTmpl.add(res[i + k * equationCount])
            }
            result.add(resultTmpl)
            k += 1
            //k += step
        }
        return result
    }

    private fun func(arr: DoubleArray, i: Int, n: Int, func: Array<Array<Double>>): Double {
        var res = 0.0
        for (j in 0 until n) {
            res += arr[j] * func[i][j]
        }
        return res
    }

}
