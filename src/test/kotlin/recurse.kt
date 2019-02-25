import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Recurse {

    // We lied
    fun recurse(input: String): String {
        var result = ""

        var currentChar = input[0]
        var count = 1
        var pos = 1

        for (pos in 1 until input.length) {
            if (input[pos] == input[pos-1]) {
                count++
            } else {
                result += "$count$currentChar"
                currentChar = input[pos]
                count = 1
            }
        }

        return result + "$count$currentChar"
    }

    @Test
    fun myTest() {
        assertEquals("31", recurse("111"))
        assertEquals("112317", recurse("1337"))

        var input = "1113122113"

        IntRange(1, 40).forEach {
            input = recurse(input)
            println(input.length)
        }

        assertEquals("", input)
    }
}