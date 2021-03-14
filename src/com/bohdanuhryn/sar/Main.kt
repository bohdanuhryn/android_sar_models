package com.bohdanuhryn.sar

import com.bohdanuhryn.sar.models.base.Model
import com.bohdanuhryn.sar.models.MobileDeviceWithRejuvenationModel
import com.bohdanuhryn.sar.models.TestModel
import com.bohdanuhryn.sar.models.UserBehaviorWithRejuvenationModel
import com.bohdanuhryn.sar.models.base.ModelTest

fun main(args: Array<String>) {
    //solveTestModel()
    solveUserBehaviorWithRejuvenationModel()
    solveMobileDeviceWithRejuvenationModel()

    testUserBehaviorWithRejuvenationModel()
    testMobileDeviceWithRejuvenationModel()
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
        maxTime = 5000.0
    )
    model.save()
}

fun solveMobileDeviceWithRejuvenationModel() {
    val model: Model = MobileDeviceWithRejuvenationModel(
        /*lamAS = 0.25,// after 4 hours
        lamSA = 0.143,// after 7 hours
        lamYR = 0.125,// after 8 hours
        lamYO = 0.01,// after 100 hours
        lamORe = 0.01,// after 100 hours
        lamReY = 60.0,// after 1 minute
        lamRY = 60.0,// after 1 minute*/

        lamAS = 0.00417,// after 4 hours
        lamSA = 0.00238,// after 7 hours
        lamYR = 0.00208,// after 8 hours
        lamYO = 0.00017,// after 100 hours
        lamORe = 0.00017,// after 100 hours
        lamReY = 1.0,// after 1 minute
        lamRY = 1.0,// after 1 minute

        /*lamSpLp = 0.042,// after 24 hours
        lamLpSp = 0.5,// after 2 hours
        lamLpOp = 1.0,// after 1 hour*/

        lamSpLp = 0.0007,// after 24 hours
        lamLpSp = 0.0083,// after 2 hours
        lamLpOp = 0.017,// after 1 hour

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
        initialRSLp = 0.0,

        initialOp = 0.0
    )
    model.solve(
        initialTime = 0.0,
        intervalTime = 1.0,
        maxTime = 3000.0
    )
    model.save()
}

fun solveUserBehaviorWithRejuvenationModel() {
    val model: Model = UserBehaviorWithRejuvenationModel(
        /*lamAS = 0.25,// after 4 hours
        lamSA = 0.143,// after 7 hours
        lamYR = 0.125,// after 8 hours
        lamYO = 0.01,// after 100 hours
        lamORe = 0.01,// after 100 hours
        lamReY = 60.0,// after 1 minute
        lamRY = 60.0,// after 1 minute*/

        lamAS = 0.00417,// after 4 hours
        lamSA = 0.00238,// after 7 hours
        lamYR = 0.00208,// after 8 hours
        lamYO = 0.00017,// after 100 hours
        lamORe = 0.00017,// after 100 hours
        lamReY = 1.0,// after 1 minute
        lamRY = 1.0,// after 1 minute

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
        maxTime = 3000.0
    )
    model.save()
}

fun testUserBehaviorWithRejuvenationModel() {
    val test = ModelTest(
        initialTime = 0.0,
        maxTime = 3000.0,
        intervalTime = 1.0
    )
    val results = test.test { lambdaYR -> UserBehaviorWithRejuvenationModel(
        /*lamAS = 0.25,// after 4 hours
        lamSA = 0.143,// after 7 hours
        lamYR = lambdaYR,// after 8 hours
        lamYO = 0.01,// after 100 hours
        lamORe = 0.01,// after 100 hours
        lamReY = 60.0,// after 1 minute
        lamRY = 60.0,// after 1 minute*/

        lamAS = 0.00417,// after 4 hours
        lamSA = 0.00238,// after 7 hours
        lamYR = lambdaYR,// after 8 hours
        lamYO = 0.00017,// after 100 hours
        lamORe = 0.00017,// after 100 hours
        lamReY = 1.0,// after 1 minute
        lamRY = 1.0,// after 1 minute

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
        maxTime = 3000.0,
        intervalTime = 1.0
    )
    val results = test.test { lambdaYR -> MobileDeviceWithRejuvenationModel(
        /*lamAS = 0.25,// after 4 hours
        lamSA = 0.143,// after 7 hours
        lamYR = lambdaYR,// after 8 hours
        lamYO = 0.01,// after 100 hours
        lamORe = 0.01,// after 100 hours
        lamReY = 60.0,// after 1 minute
        lamRY = 60.0,// after 1 minute*/

        lamAS = 0.00417,// after 4 hours
        lamSA = 0.00238,// after 7 hours
        lamYR = lambdaYR,// after 8 hours
        lamYO = 0.00017,// after 100 hours
        lamORe = 0.00017,// after 100 hours
        lamReY = 1.0,// after 1 minute
        lamRY = 1.0,// after 1 minute

        /*lamSpLp = 0.042,// after 24 hours
        lamLpSp = 0.5,// after 2 hours
        lamLpOp = 1.0,// after 1 hour*/

        lamSpLp = 0.0007,// after 24 hours
        lamLpSp = 0.0083,// after 2 hours
        lamLpOp = 0.017,// after 1 hour

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
        initialRSLp = 0.0,

        initialOp = 0.0
    ) }
    results.save()
}