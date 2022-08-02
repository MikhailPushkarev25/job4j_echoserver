package io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (values.containsKey(key)) {
            return values.get(key);
        }
        throw new IllegalArgumentException();
    }

    public void parse(String[] args) {
        Arrays.stream(args)
                .forEach(a -> {
                    String[] array = a.split("=", 2);
                    if (isValid(array)) {
                        values.put(array[0].replace("-", ""), array[1].replace("-", ""));
                    }
                });
    }

    public boolean isValid(String[] str) {

        if (str[0].isEmpty() || str[1].isEmpty()) {
            throw new IllegalArgumentException();
        }
        return true;
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
