package com.bohdanuhryn.sar

import com.bohdanuhryn.sar.models.base.Model
import com.bohdanuhryn.sar.models.MobileDeviceWithRejuvenationModel
import com.bohdanuhryn.sar.models.TestModel
import com.bohdanuhryn.sar.models.UserBehaviorWithRejuvenationModel
import com.bohdanuhryn.sar.models.base.ModelTest

fun main(args: Array<String>) {
    //solveTestModel()
    //solveUserBehaviorWithRejuvenationModel()
    //solveMobileDeviceWithRejuvenationModel()

    testUserBehaviorWithRejuvenationModel()
    //testMobileDeviceWithRejuvenationModel()
}

fun solveTestModel() {
    val model = TestModel(
        hw1 = 0.0005,
        hw2 = 0.0004,
        hw3 = 0.0003,
        hw4 = 0.00025,
        hw5 = 0.0005,
        sw2 = 0.0004,
        sw3 = 0.0003,

        initial1 = 1.0,
        initial2 = 0.0,
        initial3 = 0.0,
        initial4 = 0.0,
        initial5 = 0.0
    )
    model.solve(
        initialTime = 0.0,
        intervalTime = 1.0,
        maxTime = 500.0
    )
    model.save()
}

fun solveMobileDeviceWithRejuvenationModel() {
    val model: Model = MobileDeviceWithRejuvenationModel(
        lamAS = 0.3,
        lamSA = 0.7,
        lamYO = 0.0025,
        lamORe = 0.0025,
        lamReY = 15.0,
        lamRY = 15.0,
        lamYR = 0.018,
        lamSpLp = 0.036,
        lamLpSp = 1.0,
        lamLpOp = 0.25,

        initialSYSp = 1.0,
        initialAOSp = 0.0,
        initialRASp = 0.0,
        initialSOSp = 0.0,
        initialAReSp = 0.0,
        initialAYSp = 0.0,
        initialSReSp = 0.0,
        initialRSSp = 0.0,

        initialSYLp = 0.0,
        initialAOLp = 0.0,
        initialRALp = 0.0,
        initialSOLp = 0.0,
        initialAReLp = 0.0,
        initialAYLp = 0.0,
        initialSReLp = 0.0,
        initialRSLp = 0.0
    )
    model.solve(
        initialTime = 0.0,
        intervalTime = 1.0,
        maxTime = 500.0
    )
    model.save()
}

fun solveUserBehaviorWithRejuvenationModel() {
    val model: Model = UserBehaviorWithRejuvenationModel(
        lamAS = { x -> 0.3 },
        lamSA = { x -> 0.7 },
        lamYR = 0.125,
        lamYO = 0.0025,
        lamORe = 0.0025,
        lamReY = 15.0,
        lamRY = 15.0,

        initialSY = 1.0,
        initialAO = 0.0,
        initialRA = 0.0,
        initialSO = 0.0,
        initialARe = 0.0,
        initialAY = 0.0,
        initialSRe = 0.0,
        initialRS = 0.0
    )
    model.solve(
        initialTime = 0.0,
        intervalTime = 1.0,
        maxTime = 500.0
    )
    model.save()
}

fun testUserBehaviorWithRejuvenationModel() {
    val test = ModelTest(
        initialTime = 0.0,
        maxTime = 600.0,
        intervalTime = 1.0
    )
    val results = test.test { lambdaYR, lambdaYO -> UserBehaviorWithRejuvenationModel(
        lamAS = { x -> 0.3
            /*val h = x.times(15).div(60) % 24
            if (h in 9.0..22.0) 0.3 else 0.4*/
        },
        lamSA = { x -> 0.7
            /*val h = x.times(15).div(60) % 24
            if (h in 9.0..22.0) 0.7 else 0.6*/
        },
        lamYR = lambdaYR,
        lamYO = lambdaYO,/*0.0025,*/
        lamORe = lambdaYO,/*0.0025,*/
        lamReY = 15.0,
        lamRY = 15.0,

        initialSY = 1.0,
        initialAO = 0.0,
        initialRA = 0.0,
        initialSO = 0.0,
        initialARe = 0.0,
        initialAY = 0.0,
        initialSRe = 0.0,
        initialRS = 0.0
    ) }
    results.save()
}

fun testMobileDeviceWithRejuvenationModel() {
    val test = ModelTest(
        initialTime = 0.0,
        maxTime = 400.0,
        intervalTime = 1.0
    )
    val results = test.test { lambdaYR, lambdaYO -> MobileDeviceWithRejuvenationModel(
        lamAS = 0.3,
        lamSA = 0.7,
        lamYO = lambdaYO,//0.0025,
        lamORe = lambdaYO,//0.0025,
        lamReY = 15.0,
        lamRY = 15.0,
        lamYR = lambdaYR,

        lamSpLp = 0.036,
        lamLpSp = 1.0,
        lamLpOp = 0.25,

        initialSYSp = 1.0,
        initialAOSp = 0.0,
        initialRASp = 0.0,
        initialSOSp = 0.0,
        initialAReSp = 0.0,
        initialAYSp = 0.0,
        initialSReSp = 0.0,
        initialRSSp = 0.0,

        initialSYLp = 0.0,
        initialAOLp = 0.0,
        initialRALp = 0.0,
        initialSOLp = 0.0,
        initialAReLp = 0.0,
        initialAYLp = 0.0,
        initialSReLp = 0.0,
        initialRSLp = 0.0
    ) }
    results.save()
}