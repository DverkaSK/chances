package ru.dverkask.chance;

import java.util.ArrayList;
import java.util.List;

public class Category<T> {
    private final String          name;
    private final double          weight;
    private final List<Chance<T>> items;

    public Category(String name, double weight) {
        this.name = name;
        this.weight = weight;
        this.items = new ArrayList<>();
    }

    public void addItem(T item, double chance) {
        items.add(new Chance<>(item, chance));
    }

    public String getName() {
        return this.name;
    }

    public double getWeight() {
        return this.weight;
    }

    public List<Chance<T>> getItems() {
        return this.items;
    }
}
