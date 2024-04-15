package ru.dverkask.chance

class PercentageChanceTable<T>(chances: List<Chance<T>>) : ChanceTable<T>() {
    init {
        chances.forEach { addChance(it.item, it.chance) }
    }

    override fun addChance(item: T, chance: Double) {
        require(chance in 0.0..1.0) { "Chance must be between 0 and 1" }
        chances.add(Chance(item, chance))
    }

    override fun roll(): T? {
        val p = Math.random()
        var cumulativeProbability = 0.0
        for (chance in chances) {
            cumulativeProbability += chance.chance
            if (p <= cumulativeProbability) {
                return chance.item
            }
        }
        return null
    }
}