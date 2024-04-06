package ru.dverkask.chance;

import java.util.*;

public abstract class ChanceTable<T> {
    private final List<Chance<T>> chances = new ArrayList<>();
    private double totalWeight;
    private final Random rand = new Random();

    private ChanceTable(List<Chance<T>> chances) {
        this.chances.addAll(chances);
        chances.forEach(chance -> totalWeight += chance.chance());
    }

    @SafeVarargs public static <T> ChanceTable<T> create(Chance<T>... chances) {
        return null;
    }

    public T roll() {
        double randNum = rand.nextDouble() * totalWeight;
        for (Chance<T> chance : chances) {
            if (randNum < chance.chance()) {
                return chance.object();
            }
            randNum -= chance.chance();
        }
        throw new RuntimeException("Should never reach here if totalWeight is correctly calculated.");
    }
}