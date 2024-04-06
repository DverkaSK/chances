package ru.dverkask.chance;

public class PercentageChanceTable<T> extends ChanceTable<T> {
    @Override
    public void addChance(T item, double chance) {
        if (chance < 0 || chance > 1) {
            throw new IllegalArgumentException("Chance must be between 0 and 1");
        }
        chances.add(new Chance<>(item, chance));
    }

    @Override
    public T roll() {
        double p = Math.random();
        double cumulativeProbability = 0.0;
        for (Chance<T> chance : chances) {
            cumulativeProbability += chance.chance();
            if (p <= cumulativeProbability) {
                return chance.object();
            }
        }
        return null; // или выбросить исключение, если список пуст
    }
}
