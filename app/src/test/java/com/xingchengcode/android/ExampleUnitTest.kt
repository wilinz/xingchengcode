package com.xingchengcode.android

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
        println("17891140504".replace(Regex("""(\d{3})\d{4}(\d{4})"""), "$1****$2"))
    }
}