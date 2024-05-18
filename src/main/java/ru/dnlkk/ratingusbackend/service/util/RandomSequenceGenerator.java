package ru.dnlkk.ratingusbackend.service.util;
import java.util.Random;

public class RandomSequenceGenerator {
    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!_()[]";
    private static final int LENGTH = 11;

    public static String generateRandomSequence() {
        Random random = new Random();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < LENGTH; i++) {
            int randomIndex = random.nextInt(CHARS.length());
            builder.append(CHARS.charAt(randomIndex));
        }

        return builder.toString();
    }
}
