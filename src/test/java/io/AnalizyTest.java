package io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.time.temporal.Temporal;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AnalizyTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenTestSourceTargetOutIn() {
        String source = "C:/projects/djunior/out.txt";
        String target = "C:/projects/djunior/in.txt";
        Analizy analizy = new Analizy();
        analizy.unavailable(source, target);
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            assertThat(in.readLine(), is("10:58:01 10:59:01"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenTestSourceTargetOutInRule() throws FileNotFoundException {
       File source = new File("sour");
       File target = new File("tar");
       try (PrintWriter out = new PrintWriter(source)) {
           out.println("200 10:56:01");
           out.println("200 10:57:01");
           out.println("400 10:58:01");
           out.println("200 10:59:01");
           out.println("500 11:01:02");
           out.println("200 11:02:02");
       }
        Analizy analizy = new Analizy();
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            assertThat(in.readLine(), is("10:58:01 10:59:01"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}