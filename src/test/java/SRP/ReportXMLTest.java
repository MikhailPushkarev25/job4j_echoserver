package SRP;

import org.apache.log4j.Layout;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ReportXMLTest {

    @Test
    public void whenReportXMLGenerateToXML() throws JAXBException, IOException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Mikhail", now, now, 100);
        store.add(worker);
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SXXX");
        Report xml = new ReportXML(store);
        StringBuilder text = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<employee>\n    <salary>0.0</salary>\n")
                .append("    <list name=\"" + worker.getName() + "\">\n")
                .append("        <hired>" + isoFormat.format(worker.getHired().getTime()) + "</hired>\n")
                .append("        <fired>" + isoFormat.format(worker.getFired().getTime()) + "</fired>\n")
                .append("        <salary>" + worker.getSalary() + "</salary>\n")
                .append("    </list>\n")
                .append("</employee>\n");
        assertThat(xml.generate(em -> true), is(text.toString()));
    }

}