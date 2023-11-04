package io.github.ovso.fastingtime

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
        val answer = Solution().solution(120)
        assert(answer)
    }
}

class Solution {
    fun solution(x: Int): Boolean {
        val arr = x.toString().toCharArray().map { it.toString().toInt() }
        val sum = arr.sum()
        println(sum)
        return x % sum == 0
    }
}