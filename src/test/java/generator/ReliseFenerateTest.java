package generator;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;

public class ReliseFenerateTest {

    @Test
    public void whenGenerateTest() {
        ReliseGenerate generate = new ReliseGenerate();
        Map<String, String> map = new HashMap<>();
        String line = "I am a ${name}, Who are ${subject}? ";
        map.put("name", "Mikhail Pushkarev");
        map.put("subject", "you");
        String value = generate.produce(line, map);
        Assert.assertThat(value, is("I am a Mikhail Pushkarev, Who are you? "));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenMapGenerateArgument() {
        ReliseGenerate generate = new ReliseGenerate();
        Map<String, String> map = new HashMap<>();
        String line = "I am a ${name}, Who are ${subject}? ";
        map.put("names", "Mikhail Pushkarev");
        map.put("subjects", "you");
        String value = generate.produce(line, map);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenMapGenerateArgumentWhenNumbers() {
        ReliseGenerate generate = new ReliseGenerate();
        Map<String, String> map = new HashMap<>();
        String line = "I am a ${name}, ${surname}, Who are ${subject}? ";
        map.put("name", "Mikhail Pushkarev");
        map.put("subject", "you");
        String value = generate.produce(line, map);
    }
}