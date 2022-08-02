package gc.cashe;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringJoiner;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }


    @Override
    protected String load(String key) {
        StringJoiner joiner = new StringJoiner(System.lineSeparator());
        try (BufferedReader in = new BufferedReader(new FileReader(key + cachingDir))) {
            in.lines().forEach(joiner::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return joiner.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        DirFileCache dir = new DirFileCache("./");
        String address = dir.get("Addres.txt");
        System.out.println(address);
        String name = dir.get("Names.txt");
        System.out.println(name);
    }
}
