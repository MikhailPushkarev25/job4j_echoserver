package SRP;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.function.Predicate;

public interface Report {

    String generate(Predicate<Employee> filter) throws JAXBException, IOException;
}
