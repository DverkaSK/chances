package ru.dverkask.chance

@JvmRecord
data class Chance<T>(val `object`: T, val chance: Double)
