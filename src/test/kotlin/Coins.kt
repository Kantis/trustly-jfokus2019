import org.junit.jupiter.api.Test
import java.security.MessageDigest
import kotlin.test.assertEquals

class Coins {

    fun isCoin(input: String, number: Int): Boolean {
        if (number % 10000 == 0)
            println(number)
        val startsWith = "$input$number".md5().startsWith("00000")
        if (startsWith)
            println("Match found at $number")
        return startsWith
    }

    fun findCoin(input: String): Int {
        return IntRange(0, 999999999).dropWhile { !isCoin(input, it) }.first()
    }

    @Test
    fun example() {
        assertEquals(1048970, findCoin("ckczppom"))
    }


}

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return md.digest(this.toByteArray())
        .map { convert(it) }
        .reduce { a, b -> a + b }
}

const val hexAlpha = "0123456789ABCDEF"

fun convert(byte: Byte): String {
    val result = StringBuilder(2)

    val b = (byte + 255) % 255

    result.append(hexAlpha[(b / 16)])

    if (byte < 0) {
        result.append(hexAlpha[(b + 1) % 16])
    } else {
        result.append(hexAlpha[b % 16])
    }

    return result.toString()
}