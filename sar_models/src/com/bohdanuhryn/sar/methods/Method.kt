package com.bohdanuhryn.sar.methods

interface Method {

    fun solve(initialX: Double, deltaX: Double, maxX: Double, initialY: List<Double>): List<List<Double>>

}