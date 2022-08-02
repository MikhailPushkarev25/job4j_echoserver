package io;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "C:/projects/djunior/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.dialect"),is("org.hibernate.dialect.PostgreSQLDialect"));
    }

    @Test
    public void whenPairWithoutNullComment() {
        String path = "C:/projects/djunior/app.properties";
        Config config = new Config(path);
        config.load();
        assertNull(config.value("#"),null);
    }

    @Test
    public void whenPairWithoutNull() {
        String path = "C:/projects/djunior/app.properties";
        Config config = new Config(path);
        config.load();
        assertNull(config.value(" "),null);
    }

}