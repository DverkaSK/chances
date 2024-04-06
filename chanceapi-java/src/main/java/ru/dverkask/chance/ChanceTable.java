package ru.dverkask.chance;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public abstract class ChanceTable<T> implements Iterable<T> {
    protected List<Chance<T>> chances = new ArrayList<>();
    protected ChanceTable() {

    }

    @SafeVarargs public static <T> ChanceTable<T> create(ChanceType type, Chance<T>... chances) {
        return switch (type) {
            case PERCENTAGE -> new PercentageChanceTable<>(chances);
            case WEIGHT -> new WeightChanceTable<>(chances);
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }

    @NotNull @Override public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final Iterator<Chance<T>> innerIterator = chances.iterator();
            @Override public boolean hasNext() {
                return innerIterator.hasNext();
            }

            @Override public T next() {
                return innerIterator.next().item();
            }
        };
    }

    public abstract void addChance(T item, double chance);

    public abstract T roll();
}