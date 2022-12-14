package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        isValid(args);

        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles files = new SearchFiles(condition);
        Files.walkFileTree(root, files);
        return files.getPaths();
    }

    private static void isValid(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar search.jar ROOT_FOLDER.");
        }

        if (args.length < 2) {
            throw new IllegalArgumentException("Root folder is null.");
        }
    }
}
