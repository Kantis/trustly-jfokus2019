import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Primes {

    object Sieve {
        fun primesUpTo(max: Int): List<Int> {
            tailrec fun helper(primes: List<Int>, possiblePrimes: List<Int>): List<Int> =
                when {
                    possiblePrimes.isEmpty() -> primes
                    else -> {
                        val prime = possiblePrimes.first()
                        val sievedPossiblePrimes = possiblePrimes.filter { it % prime != 0 }
                        helper(primes + prime, sievedPossiblePrimes)
                    }
                }

            return helper(emptyList(), (2 .. max).toList())
        }
    }

    @Test
    fun example() {
        assertEquals(107, Sieve.primesUpTo(40).filter { it.toString().contains("3") }.sum())
    }

    @Test
    fun question() {
        println("Answer: ${Sieve.primesUpTo(40_000).filter { it.toString().contains("3") }.sum()}")
    }
}