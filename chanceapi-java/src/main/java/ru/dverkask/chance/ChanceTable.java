package ru.dverkask.chance;

import java.util.*;

public abstract class ChanceTable<T> {
    protected List<Chance<T>> chances = new ArrayList<>();

    public static <T> ChanceTable<T> create(ChanceType type) {
        return switch (type) {
            case PERCENTAGE -> new PercentageChanceTable<>();
            case WEIGHT -> new WeightChanceTable<>();
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }

    public abstract void addChance(T item, double chance);

    public abstract T roll();

    public enum ChanceType {
        PERCENTAGE, WEIGHT
    }
}