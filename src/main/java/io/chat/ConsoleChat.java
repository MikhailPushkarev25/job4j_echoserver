package io.chat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ConsoleChat {

    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public boolean flag = true;
    public List<String> log = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        String str = " ";
        Random random = new Random();
       while (!OUT.equals(str)) {
            System.out.println("Продолжайте общение с ботом: ");
            str = scanner.nextLine();
            log.add(str);
        if (STOP.contains(str)) {
            System.out.println("Вы остановили чат: ");
            flag = false;
        }

        if (CONTINUE.contains(str)) {
            flag = true;
        }
            if(!str.contains(OUT)&&!str.contains(STOP)&&flag) {
                String answer = readPhrases().get(random.nextInt(readPhrases().size()));
                System.out.println(answer);
                log.add(answer);
            }
    }

    saveLog(log);

}

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().map(s -> s + System.lineSeparator()).forEach(list::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(this.botAnswers, StandardCharsets.UTF_8), true)) {
            log.forEach(pw::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat chat = new ConsoleChat("chat.txt", "answer.txt");
        chat.run();
    }
}
