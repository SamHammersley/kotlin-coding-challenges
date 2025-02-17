package com.igorwojda.integer.fibonacci.basic

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun fibonacci(n: Int): Int {
    var fibn2 = 0
    var fibn1 = 1
    var fibn = fibn2 + fibn1

    for (i in 1..n) {
        fibn2 = fibn1
        fibn1 = fibn
        fibn = fibn2 + fibn1
    }

    return fibn2
}

private class Test {
    @Test
    fun `calculates correct fib value for 0`() {
        fibonacci(0) shouldBeEqualTo 0
    }

    @Test
    fun `calculates correct fib value for 1`() {
        fibonacci(1) shouldBeEqualTo 1
    }

    @Test
    fun `calculates correct fib value for 2`() {
        fibonacci(2) shouldBeEqualTo 1
    }

    @Test
    fun `calculates correct fib value for 3`() {
        fibonacci(3) shouldBeEqualTo 2
    }

    @Test
    fun `calculates correct fib value for 4`() {
        fibonacci(4) shouldBeEqualTo 3
    }

    @Test
    fun `calculates correct fib value for 15`() {
        fibonacci(39) shouldBeEqualTo 63245986
    }
}
