package org.alvio.flightcli.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class AppUtil {

    public static <T> List<String> generateStringReport(List<T> items, Function<T, String> mapper) {
        if (items == null || items.isEmpty()) { return new ArrayList<>(0); }
        List<String> result = new ArrayList<>(items.size());
        for (T item : items) { result.add(mapper.apply(item)); }
        return result;
    }

    public static void printQueryReport(List<String> reportItems) {
        for (String item : reportItems) {
            System.out.println(item + "\n");
        }
    }
}