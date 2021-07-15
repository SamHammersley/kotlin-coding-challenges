package com.igorwojda.matrix.spiralmatrixgenerator

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

private fun generateSpiralMatrix(n: Int): List<MutableList<Int>> {
    val matrix = Array(n) { Array(n) { n*n } }
    var currentValue = 0
    var currentRow = 0
    var currentColumn = 0

    vectors(n).forEach { vector ->
        val direction = vector.first
        val distance = vector.second

        repeat(distance) {
            matrix[currentRow][currentColumn] = 1 + currentValue++

            when (direction) {
                0 -> currentColumn++
                1 -> currentRow++
                2 -> currentColumn--
                3 -> currentRow--
            }
        }
    }

    return matrix.map { it.toMutableList() }
}

private fun vectors(n: Int): List<Pair<Int, Int>> {
    val vectors = (n*2-1 downTo 2)
        .mapIndexed { index, distance -> Pair((1 + index) % 4, distance / 2) }

    return mutableListOf(Pair(0, n-1)).plus(vectors)
}

private class Test {
    @Test
    fun `generateSpiralMatrix generates a 2x2 matrix`() {
        val matrix = generateSpiralMatrix(2)
        matrix.size shouldBeEqualTo 2
        matrix[0] shouldBeEqualTo listOf(1, 2)
        matrix[1] shouldBeEqualTo listOf(4, 3)
    }

    @Test
    fun `generateSpiralMatrix generates a 3x3 matrix`() {
        val matrix = generateSpiralMatrix(3)
        matrix.size shouldBeEqualTo 3
        matrix[0] shouldBeEqualTo listOf(1, 2, 3)
        matrix[1] shouldBeEqualTo listOf(8, 9, 4)
        matrix[2] shouldBeEqualTo listOf(7, 6, 5)
    }

    @Test
    fun `generateSpiralMatrix generates a 4x4 matrix`() {
        val matrix = generateSpiralMatrix(4)
        matrix.size shouldBeEqualTo 4
        matrix[0] shouldBeEqualTo listOf(1, 2, 3, 4)
        matrix[1] shouldBeEqualTo listOf(12, 13, 14, 5)
        matrix[2] shouldBeEqualTo listOf(11, 16, 15, 6)
        matrix[3] shouldBeEqualTo listOf(10, 9, 8, 7)
    }
}
