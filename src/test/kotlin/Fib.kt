import org.junit.jupiter.api.Test

class Fib {

    fun fib(max: Int): List<Int> {
        val values = arrayListOf(1, 2)

        while (values[values.size-1] + values[values.size-2] < max) {
            values.add(values[values.size-1] + values[values.size-2])
        }

        return values
    }

    @Test
    fun dmmy() {
        print(fib(10_000_000).filter { it % 2 == 1 }.sum())
    }

}