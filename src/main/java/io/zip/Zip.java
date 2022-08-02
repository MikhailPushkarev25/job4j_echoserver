package io.zip;

import io.ArgsName;
import io.Search;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFile(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : sources) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] isValid(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("This is not arguments!");
        }

        ArgsName name = ArgsName.of(args);

        if (!Files.isDirectory(Path.of(name.get("d")))) {
            throw new IllegalArgumentException("This is not directory!");
        }

        if (!name.get("e").startsWith(".")) {
            throw new IllegalArgumentException("This is not found starts!");
        }
        return new String[] {name.get("d"), name.get("e"), name.get("o")};
    }

    public static List<File> getListFile(String path, String exp) throws IOException {
        return Search.search(Path.of(path), p -> !p.toFile().getName().endsWith(exp))
                .stream()
                .map(Path::toFile)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        String[] array = isValid(args);
        List<File> list = getListFile(String.valueOf(Path.of(array[0])), array[1]);
        zip.packFile(list, Path.of(array[2]).toFile());
    }
}
