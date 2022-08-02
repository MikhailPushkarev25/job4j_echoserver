package SRP;

import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ReportEngineTest {


    @Test
    public void whenHtmlGenerate() throws JAXBException, IOException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Mikhail", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html>")
                .append("<title>Name; Hired; Fired; Salary</title>")
                .append("<html>")
                .append("<tr>")
                .append("<th>").append(worker.getName()).append("</th>")
                .append("<th>").append(worker.getHired()).append("</th>")
                .append("<th>").append(worker.getFired()).append("</th>")
                .append("<th>").append(worker.getSalary()).append("</th>")
                .append("</html>")
                .append("</tr>")
                .append(System.lineSeparator());
        assertThat(engine.generate((em -> true)), is(expect.toString()));
    }

    @Test
    public void whenConverter() throws JAXBException, IOException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Mikhail", now, now, 100);
        store.add(worker);
        Report engine = new ReportAccounting(store);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html>")
                .append("<title>Name; Hired; Fired; Salary</title>")
                .append("<html>")
                .append("<tr>")
                .append("<th>").append(worker.getName()).append("</th>")
                .append("<th>").append(worker.getHired()).append("</th>")
                .append("<th>").append(worker.getFired()).append("</th>")
                .append("<th>").append(worker.getSalary() / 58).append("</th>")
                .append("</html>")
                .append("</tr>")
                .append(System.lineSeparator());
        assertThat(engine.generate((em -> true)), is(expect.toString()));
    }

    @Test
    public void whenSalaryCut() throws JAXBException, IOException {
        MemStore store = new MemStore();
        Employee worker = new Employee("Mikhail", 100);
        store.add(worker);
        Report engine = new ReportHR(store);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html>")
                .append("<title>Name; Salary</title>")
                .append("<html>")
                .append("<tr>")
                .append("<th>").append(worker.getName()).append("</th>")
                .append("<th>").append(worker.getSalary()).append("</th>")
                .append("</html>")
                .append("</tr>")
                .append(System.lineSeparator());
        assertThat(engine.generate((em -> true)), is(expect.toString()));
    }
}