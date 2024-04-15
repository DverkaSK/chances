package ru.dverkask.chance

class WeightChanceTable<T>(chances: List<Chance<T>>) : ChanceTable<T>() {
    init {
        chances.forEach { addChance(it.item, it.chance) }
    }

    override fun addChance(item: T, weight: Double) {
        require(weight >= 0) { "Weight must be positive" }
        chances.add(Chance(item, weight))
    }

    override fun roll(): T? {
        val totalWeight = chances.sumOf { it.chance }
        var r = Math.random() * totalWeight
        var countWeight = 0.0
        for (chance in chances) {
            countWeight += chance.chance
            if (r < countWeight) {
                return chance.item
            }
        }
        return null
    }
}