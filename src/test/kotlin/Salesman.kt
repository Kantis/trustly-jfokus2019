
import org.junit.jupiter.api.Test

enum class City {
    Stockholm, Gothenburg, Malmo, Paris, Rome, London, Berlin, Amsterdam
}

class CityCost(
    val cityPair: Pair<City, City>,
    val cost: Int
)

val costs =
    listOf(
        CityCost(Pair(City.Stockholm, City.Gothenburg), 65),
        CityCost(Pair(City.Stockholm, City.Malmo), 129),
        CityCost(Pair(City.Stockholm, City.Rome), 144),
        CityCost(Pair(City.Stockholm, City.Berlin), 71),
        CityCost(Pair(City.Stockholm, City.London), 137),
        CityCost(Pair(City.Stockholm, City.Paris), 3),
        CityCost(Pair(City.Stockholm, City.Amsterdam), 149),
        CityCost(Pair(City.Gothenburg, City.Malmo), 63),
        CityCost(Pair(City.Gothenburg, City.Rome), 4),
        CityCost(Pair(City.Gothenburg, City.Berlin), 105),
        CityCost(Pair(City.Gothenburg, City.London), 125),
        CityCost(Pair(City.Gothenburg, City.Paris), 55),
        CityCost(Pair(City.Gothenburg, City.Amsterdam), 14),
        CityCost(Pair(City.Malmo, City.Rome), 68),
        CityCost(Pair(City.Malmo, City.Berlin), 52),
        CityCost(Pair(City.Malmo, City.London), 65),
        CityCost(Pair(City.Malmo, City.Paris), 22),
        CityCost(Pair(City.Malmo, City.Amsterdam), 143),
        CityCost(Pair(City.Rome, City.Berlin), 8),
        CityCost(Pair(City.Rome, City.London), 23),
        CityCost(Pair(City.Rome, City.Paris), 136),
        CityCost(Pair(City.Rome, City.Amsterdam), 115),
        CityCost(Pair(City.Berlin, City.London), 101),
        CityCost(Pair(City.Berlin, City.Paris), 84),
        CityCost(Pair(City.Berlin, City.Amsterdam), 96),
        CityCost(Pair(City.London, City.Paris), 107),
        CityCost(Pair(City.London, City.Amsterdam), 14),
        CityCost(Pair(City.Paris, City.Amsterdam), 46)
    )

fun getCost(c1: City, c2: City): Int {
    return costs.first { it.cityPair == Pair(c1, c2) || it.cityPair == Pair(c2, c1) }.cost
}

class Salesman {
    // If size of S is 2, then S must be {1, i},
    //   C(S, i) = dist(1, i)
    // Else if size of S is greater than 2.
    //   C(S, i) = min { C(S-{i}, j) + dis(j, i)} where j belongs to S, j != i and j != 1.

    fun cost(route: List<City>): Int {
        var currentCity = route[0]
        var result = 0

        for (city in route.subList(1, route.size - 1)) {
            result += getCost(currentCity, city)
            currentCity = city
        }

        return result
    }

    fun permutate(cities: List<City>): Set<MutableList<City>> {
        if (cities.size == 1)
            return setOf(mutableListOf(cities[0]))
        else {
            val permutations = mutableSetOf<MutableList<City>>()

            for (city in cities) {
                val otherCities = cities.filter { it != city }
                val result = permutate(otherCities)
                result.forEach { it.add(city) }

                permutations.addAll(result)
            }

            return permutations
        }
    }

    @Test
    fun `sthlm gbg malmo`() {
        val message = permutate(City.values().toList())
        println(message.map { cost(it) }.min())
    }
}
