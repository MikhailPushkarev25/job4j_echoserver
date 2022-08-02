package jdbc;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDb {

    private Properties cfg;
    private String dump;

    public ImportDb(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws FileNotFoundException {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dump))) {
            reader.lines().map(u -> {
                String[] line = u.split(";");
                return new User(line[0], line[1]);
            }).forEach(users::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void save(List<User> users) throws SQLException, ClassNotFoundException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection con = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps =
                             con.prepareStatement("insert into users(name, email) values (?,?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {

        private String name;
        private String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        Properties cfg = new Properties();
        try (InputStream in = ImportDb.class.getClassLoader().getResourceAsStream("app.properties")) {
            cfg.load(in);
        }
        ImportDb importDb = new ImportDb(cfg, "./dump.txt");
        importDb.save(importDb.load());
    }
}
