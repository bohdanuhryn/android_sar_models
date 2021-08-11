package com.bohdanuhryn.sar.methods

import com.sun.javaws.exceptions.InvalidArgumentException

class RungeKuttaMethod2(
    val equations: List<Equation>
) : Method {

    override fun solve(initialX: Double, deltaX: Double, maxX: Double, initialY: List<Double>): List<List<Double>> {
        if (initialY.size != equations.size) {
            throw InvalidArgumentException(arrayOf("initialY size != equations count"))
        }
        val result: ArrayList<List<Double>> = arrayListOf<List<Double>>().apply { add(initialY) }

        val k1: MutableList<Double> = MutableList(initialY.size) { 0.0 }
        val k2: MutableList<Double> = MutableList(initialY.size) { 0.0 }
        val k3: MutableList<Double> = MutableList(initialY.size) { 0.0 }
        val k4: MutableList<Double> = MutableList(initialY.size) { 0.0 }

        var t = initialX
        while (t < maxX) {

            for (i in equations.indices) {
                k1[i] = equations[i](t, result.last()) * deltaX
            }

            for (i in equations.indices) {
                val tmp = k1.map { it / 2.0 }.toList()
                val y = result.last().mapIndexed { index, d -> d + tmp[index] }.toList()
                k2[i] = equations[i](t, y) * deltaX
            }

            for (i in equations.indices) {
                val tmp = k2.map { it / 2.0 }.toList()
                val y = result.last().mapIndexed { index, d -> d + tmp[index] }.toList()
                k3[i] = equations[i](t, y) * deltaX
            }

            for (i in equations.indices) {
                val tmp = k3.map { it }.toList()
                val y = result.last().mapIndexed { index, d -> d + tmp[index] }.toList()
                k4[i] = equations[i](t, y) * deltaX
            }

            val k = k1.mapIndexed { index, d -> d + (2.0 * k2[index]) + (2.0 * k3[index]) + k4[index] }
                .map { it / 6.0 }
                .toList()
            val nextY = result.last().mapIndexed { index, d -> d + k[index] }.toList()
            result.add(nextY)

            t += deltaX
        }

        return result
    }
}
