package com.boerma.npcgenerator.utility;

import com.boerma.npcgenerator.domain.Gender;

import java.util.List;
import java.util.Random;

public class RandomizationUtility {

    private <T> T getRandomElement(List<T> list) {
        if (list.isEmpty()) {
            throw new IllegalStateException("Cannot get random element from an empty list.");
        }
        return list.get(new Random().nextInt(list.size()));
    }

    public Gender getRandomGender() {
        return getRandomElement(List.of(Gender.values()));
    }

}
