package com.github.EmmanueleBollino.solcraft.helpers;

import java.util.stream.Stream;

/**
 * Helper functions for Solidity related strings.
 */
public class StringHelper {
    public static String joinCamelCase(String... strings) {
        StringBuilder result = new StringBuilder();
        Stream.of(strings)
                .map(StringHelper::capitalize)
                .forEach(result::append);

        return result.toString();
    }

    public static String capitalize(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
    }

    public static String decapitalize(String string) {
        return string.substring(0, 1).toLowerCase() + string.substring(1);
    }

    public static String privatize(String string) {
        return string.startsWith("_") ? (string) : ("_" + string);
    }
}
