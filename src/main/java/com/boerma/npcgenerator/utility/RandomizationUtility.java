package com.boerma.npcgenerator.utility;

import java.util.List;
import java.util.Random;

public class RandomizationUtility {

    public static <T> T getRandomElement(List<T> list) {
        if (list.isEmpty()) {
            throw new IllegalStateException("Cannot get random element from an empty list.");
        }
        return list.get(new Random().nextInt(list.size()));
    }

    public static int getRandomInt(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Minimum value cannot be greater than maximum value.");
        }
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

}
