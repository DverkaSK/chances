package ru.dverkask.chance

abstract class ChanceTable<T> : Iterable<T> {
    protected val chances = mutableListOf<Chance<T>>()

    companion object {
        inline fun <reified T> create(type: ChanceType, vararg chances: Chance<T>): ChanceTable<T> = when (type) {
            ChanceType.PERCENTAGE -> PercentageChanceTable(chances.toList())
            ChanceType.WEIGHT -> WeightChanceTable(chances.toList())
        }
    }

    override fun iterator(): Iterator<T> = object : Iterator<T> {
        private val innerIterator = chances.iterator()
        override fun hasNext(): Boolean = innerIterator.hasNext()
        override fun next(): T = innerIterator.next().item
    }

    abstract fun addChance(item: T, chance: Double)
    fun addCategory(category: Category<T>) {
        category.getItems().forEach { addChance(it.item, it.chance) }
    }

    abstract fun roll(): T?

    override fun toString(): String = buildString {
        append("ChanceTable{chances=[")
        chances.joinTo(this, separator = ", ") { chance ->
            "Chance{item=${chance.item}, chance=${chance.chance}}"
        }
        append("]}")
    }
}