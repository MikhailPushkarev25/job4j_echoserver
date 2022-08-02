package io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LogFile {

    public List<String> filter(String file) {
        List<String> str = new ArrayList<>();
        String line = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
               line =  reader.readLine();
                String[] result = line.split(" ");
                if (result[result.length - 2].equals("404")) {
                    str.add(line);
                }
                }
            } catch (IOException ex) {
            ex.printStackTrace();
        }
        return str;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)))) {
            for (String line : log) {
                out.write(line + System.lineSeparator());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFile logFile = new LogFile();
        List<String> log = logFile.filter("log.txt");
        save(log, "404.txt");
        System.out.println(log);
     }
}
