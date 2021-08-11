package com.bohdanuhryn.sar.methods

import flanagan.integration.DerivnFunction
import flanagan.integration.RungeKutta

class RungeKuttaMethodFlanagan(
    val equations: List<Equation>
) : Method {

    override fun solve(initialX: Double, deltaX: Double, maxX: Double, initialY: List<Double>): List<List<Double>> {
        val rk = RungeKutta()
        val results: ArrayList<List<Double>> = arrayListOf()
        results.add(initialY)
        val derivn = DerivnFunction { x: Double, y: DoubleArray? -> equations.map { it(x, y?.toList() ?: emptyList()) }.toDoubleArray() }
        var x = initialX
        while (x < maxX) {
            rk.setInitialValueOfX(x)
            rk.setInitialValuesOfY(results.last().toDoubleArray())
            rk.setFinalValueOfX(x + deltaX)
            rk.setStepSize(deltaX)
            results.add(rk.fourthOrder(derivn).toList())
            //results.add(rk.cashKarp(derivn).toList())
            //results.add(rk.fehlberg(derivn).toList())
            x += deltaX
        }

        /*rk.setInitialValueOfX(initialX)
        rk.setStepSize(deltaX)
        rk.setFinalValueOfX(maxX)
        rk.setInitialValuesOfY(initialY.toDoubleArray())
        val points: Int = ((maxX - initialX) / deltaX).toInt()
        val res = rk.fourthOrder(derivn, points)
        (0 until points).forEach { i ->
            val r: ArrayList<Double> = arrayListOf()
            equations.indices.forEach { j ->
                r.add(res[j + 1][i])
            }
            results.add(r)
        }*/

        return results
    }
}
