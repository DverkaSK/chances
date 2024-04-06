package ru.dverkask;

import ru.dverkask.chance.Chance;
import ru.dverkask.chance.ChanceTable;
import ru.dverkask.chance.ChanceType;

import java.lang.reflect.Field;

public class Main {
    private       String         str   = "213";
    private final ChanceTable<?> table = ChanceTable.create();
    public static void main(String[] args) {
        @ChanceType(value = ChanceType.Type.WEIGHT)
        ChanceTable<?> chanceTable = ChanceTable.create(
                new Chance<>("1", 1),
                new Chance<>("2", 1),
                new Chance<>("3", 1),
                new Chance<>("4", 1),
                new Chance<>("5", 1),
                new Chance<>("6", 1),
                new Chance<>("7", 1),
                new Chance<>("8", 1),
                new Chance<>("9", 1),
                new Chance<>("10", 1)
        );

        for (int i = 0; i < 10; i++) {
            System.out.println(chanceTable.roll());
        }

        for (Field field : Main.class.getDeclaredFields()) {
            System.out.println(field.getName());
        }
    }
}