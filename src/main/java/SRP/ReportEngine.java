package SRP;

import java.util.List;
import java.util.function.Predicate;

public class ReportEngine implements Report {

    private Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        for (Employee employee : store.findBy(filter)) {
                    text.append("<!DOCTYPE html>")
                            .append("<title>Name; Hired; Fired; Salary</title>")
                            .append("<html>")
                            .append("<tr>")
                            .append("<th>").append(employee.getName()).append("</th>")
                            .append("<th>").append(employee.getHired()).append("</th>")
                            .append("<th>").append(employee.getFired()).append("</th>")
                            .append("<th>").append(employee.getSalary()).append("</th>")
                            .append("</html>")
                            .append("</tr>")
                            .append(System.lineSeparator());
        }
        return text.toString();
    }
}
