package io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

    public static void handle(ArgsName argsName) throws IOException {
        File path = new File(argsName.get("path"));
        String delimiter = argsName.get("delimiter");
        String filter = String.join(delimiter, Arrays.asList(argsName.get("filter").split(",")));
        String out = argsName.get("out");

        List<String> indexLine = new ArrayList<>();
        List<String> filterCsv = new ArrayList<>();

        try (Scanner scanner = new Scanner(path)) {
            List<String> firstLine = Arrays.asList(scanner.nextLine().split(delimiter));
            int i = 0;
            while (i < firstLine.size()) {
                if (filter.contains(firstLine.get(i))) {
                    indexLine.add(String.valueOf(firstLine.indexOf(firstLine.get(i))));
                }
                i++;
            }
            while (scanner.hasNext()) {
                String[] s = scanner.nextLine().split(delimiter);
                int j = 0;
                List<String> second = new ArrayList<>();
                while (j < indexLine.size()) {
                    second.add(s[Integer.parseInt(indexLine.get(j))]);
                    j++;
                }
                filterCsv.add(String.join(delimiter, second));
            }

            if (out.equals("stdout")) {
                filterCsv.forEach(System.out::println);
            } else {
                try (PrintWriter pw = new PrintWriter(new FileWriter(out))) {
                    filterCsv.forEach(pw::println);
                }
            }
        }
    }

    private static void isValid(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Not found argument!");
        }

        if (!Files.exists(Path.of(ArgsName.of(new String[] {args[0]}).get("path")))) {
            throw new IllegalArgumentException("Not found path!");
        }
    }

    public static void main(String[] args) throws IOException {
        isValid(args);
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
