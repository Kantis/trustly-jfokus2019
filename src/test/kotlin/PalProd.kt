
import org.junit.jupiter.api.Test
import kotlin.math.log10
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PalProd {

    fun maxPal(): Long {
        var max = 1.toLong()
        var p1 = 1
        var p2 = 1


        for (i in 99999 downTo 10000) {
            for (j in 99999 downTo i) {
                val product = j * i.toLong()

                if (product < max)
                    break

                if (product.toString().isPalindrome() && product > max) {
                    max = product
                    p1 = j
                    p2 = i
                }

            }
        }

        println("Max found at $p1 times $p2: $max")

        return max
    }


    @Test
    fun example() {
        assertEquals(9009, maxPal())
    }

    @Test
    fun `isPalindrome works properly`() {
        assertTrue("anna".isPalindrome())
        assertTrue("ana".isPalindrome())
    }

    @Test
    fun `length of int`() {
        for (i in 0..9) {
            assertEquals(1, i.length(), message = i.toString())
        }

        for (i in 10..99) {
            assertEquals(2, i.length(), message = i.toString())
        }
    }

    @Test
    fun `digit at`() {
        assertEquals(1, 1.digitAt(0))
        assertEquals(1, 10.digitAt(0))
        assertEquals(0, 10.digitAt(1))
    }

    @Test
    fun `int pal`() {
        assertTrue(121.isPalindrome())
        assertFalse(123.isPalindrome())
        assertTrue(11.isPalindrome())
        assertTrue(1.isPalindrome())
    }

}

fun Int.isPalindrome(): Boolean {
    if (this in 0..9)
        return true

    val length = this.length()

    for (i in 0 until length)
        if (this.digitAt(i) != this.digitAt(length - i - 1))
            return false

    return true
}

fun Int.length(): Int {
    if (this in 0..1)
        return 1

    return Math.ceil(log10(this.toDouble() + 1)).toInt()
}

fun Int.digitAt(position: Int): Int {
    return this / pow(10, this.length() - position - 1) % 10
}

fun pow(base: Int, exponent: Int): Int {
    if (exponent == 0)
        return 1

    return IntRange(1, exponent)
        .map { base }
        .reduce(Int::times)
}

fun String.isPalindrome(): Boolean {
    val halfLength = this.length / 2
    return this.substring(0, halfLength).reversed() == this.substring(halfLength + this.length % 2)
}