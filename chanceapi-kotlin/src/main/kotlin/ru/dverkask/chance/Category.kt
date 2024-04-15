package ru.dverkask.chance

class Category<T>(
    val name: String? = null,
    val weight: Double
) {
    private val items: MutableList<Chance<T>> = mutableListOf()

    fun addItem(item: T, chance: Double = weight) {
        items.add(Chance(item, chance))
    }

    fun getItems(): List<Chance<T>> = items
}