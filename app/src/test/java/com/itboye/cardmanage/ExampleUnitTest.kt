package com.itboye.cardmanage

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun cal() {
        var num1 = "0.5"
        var num2 = "2.0"
        var num3 = "25"
        System.out.println(num3.toDouble() * num1.toDouble() - num2.toDouble())
    }
}
