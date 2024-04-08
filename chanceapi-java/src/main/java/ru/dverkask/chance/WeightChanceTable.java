package ru.dverkask.chance;

public class WeightChanceTable<T> extends ChanceTable<T> {
    @SafeVarargs protected WeightChanceTable(Chance<T>... chances) {
        for (Chance<T> chance : chances) {
            this.addChance(chance.item(), chance.chance());
        }
    }
    @Override
    public void addChance(T item, double weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        chances.add(new Chance<>(item, weight));
    }

    @Override
    public T roll() {
        double totalWeight = chances.stream().mapToDouble(Chance::chance).sum();
        double r = Math.random() * totalWeight;
        double countWeight = 0.0;
        for (Chance<T> chance : chances) {
            countWeight += chance.chance();
            if (r < countWeight) {
                return chance.item();
            }
        }
        return null;
    }
}
