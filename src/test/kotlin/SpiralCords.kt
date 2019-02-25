import org.junit.jupiter.api.Test

data class Position(
    val x: Int,
    val y: Int,
    val number: Int
) {
    fun up(): Position {
        return Position(x, y + 1, number + 1)
    }

    fun down(): Position {
        return Position(x, y - 1, number + 1)
    }

    fun right(): Position {
        return Position(x + 1, y, number + 1)
    }

    fun left(): Position {
        return Position(x - 1, y, number + 1)
    }
}

class SpiralCords {

    fun generateNextLayer(map: MutableList<Position>) {
        val start = map.last()
        // step right
        var position = start.right()
        map += position


        val size = 1 + start.x - start.y // y is negative

        for (i in 1..size) {
            position = position.up()
            map += position
        }

        // Then go one extra step in all directions
        for (moveFunc in listOf(Position::left, Position::down, Position::right)) {
            for (i in 1..size + 1) {
                position = moveFunc(position)
                map += position
            }
        }
    }

    @Test
    fun layer0() {
        val map = mutableListOf(Position(0, 0, 1))
        for (i in  1 .. 537)
            generateNextLayer(map)

        println(map.first { it.number == 289326 })
    }
}
