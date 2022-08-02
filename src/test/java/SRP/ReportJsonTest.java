package SRP;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ReportJsonTest {

    @Test
    public void whenReportGenerateToJson() throws JAXBException, IOException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Mikhail", now, now, 100);
        store.add(worker);
        Gson toJson = new GsonBuilder().create();
        Report json = new ReportJson(store);
        StringBuilder text = new StringBuilder()
                .append("[{\"")
                        .append("name\":" + toJson.toJson(worker.getName())).append(",")
                        .append("\"hired\":" + toJson.toJson(worker.getHired())).append(",")
                        .append("\"fired\":" + toJson.toJson(worker.getFired())).append(",")
                        .append("\"salary\":" + toJson.toJson(worker.getSalary())).append("}]");
        assertThat(json.generate(em -> true), is(text.toString()));
    }
}