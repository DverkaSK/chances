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
    public void addCategory(Category<T> category) {
        for (Chance<T> item : category.getItems()) {
            this.addChance(item.item(), item.chance());
        }
    }

    public abstract T roll();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ChanceTable{chances=[");
        for (int i = 0; i < chances.size(); i++) {
            Chance<T> chance = chances.get(i);
            sb.append("Chance{item=").append(chance.item().toString())
                    .append(", chance=").append(chance.chance()).append("}");
            if (i < chances.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]}");
        return sb.toString();
    }
}