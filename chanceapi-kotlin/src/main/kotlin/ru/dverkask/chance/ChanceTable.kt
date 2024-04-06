package ru.dverkask.chance

import java.util.*
import java.util.function.Consumer

class ChanceTable<T> private constructor(chances: List<Chance<T>>) {
    private val chances: MutableList<Chance<T>> = ArrayList()
    private var totalWeight = 0.0
    private val rand: Random = Random()

    init {
        this.chances.addAll(chances)
        chances.forEach(Consumer { chance: Chance<T> -> totalWeight += chance.chance })
    }

    fun roll(): T {
        var randNum = rand.nextDouble() * totalWeight
        for (chance in chances) {
            if (randNum < chance.chance) {
                return chance.`object`
            }
            randNum -= chance.chance
        }
        throw RuntimeException("Should never reach here if totalWeight is correctly calculated.")
    }

    companion object {
        @SafeVarargs
        fun <T> create(vararg chances: Chance<T>): ChanceTable<T> {
            return ChanceTable(listOf(*chances))
        }
    }
}
