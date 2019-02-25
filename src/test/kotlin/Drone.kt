import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Drone {

    data class Coordinate(
        val x: Int,
        val y: Int
    )

    var position = Coordinate(0, 0)

    private val visited = mutableSetOf<Coordinate>()

    private fun move(dir: Char) {

        when (dir) {
            'N' -> position = Coordinate(position.x, position.y + 1)
            'S' -> position = Coordinate(position.x, position.y - 1)
            'E' -> position = Coordinate(position.x + 1, position.y)
            'W' -> position = Coordinate(position.x - 1, position.y)
        }

        visited += position

    }

    @Test
    fun example() {
        val d = Drone()
        "NESW".forEach { d.move(it) }
        assertEquals(4, d.visited.size)
    }

    @Test
    fun question() {
        val d = Drone()
        """NENESNSSWWWNWENESWNNNNEEEENSSEEEEENNNNESNNESNNNNEEEENSSWWEENNNNN
            |ESNNNNEEENNNNESNNEEENNNNEEEEEENWWWWWWWSEENESNNNEEENSSEEEEENNNW
            |SNESNSSWWEEEEEWWSNESNSSWWEEEENWWWWWWWSEENESNNNEEENSSEEEEENNNNE
            |SNNNNEEENNNNESNNEEENNNNEEEEEEEEENSSEEEEENNNNESNN""".trimMargin().forEach { d.move(it) }
        assertEquals(4, d.visited.size)
    }
}