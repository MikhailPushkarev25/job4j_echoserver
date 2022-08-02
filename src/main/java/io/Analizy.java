package io;

import java.io.*;

public class Analizy {

    public void unavailable(String source, String target) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             BufferedWriter out = new BufferedWriter(new FileWriter(target))) {
            String str = in.readLine();
            String line = null;
            boolean rsl = true;
            while (str != null) {
                if (rsl && (str.startsWith("400") || str.startsWith("500"))) {
                   line = str.substring(4);
                    rsl = false;
                }
                if (!rsl && (str.startsWith("200") || str.startsWith("300"))) {
                    builder.append(line.strip())
                            .append(" ")
                            .append(str.substring(4).strip())
                            .append(System.lineSeparator());
                    rsl = true;
                }
                str = in.readLine();
            }
            out.write(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
