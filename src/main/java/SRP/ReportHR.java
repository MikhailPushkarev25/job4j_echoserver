package SRP;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ReportHR implements Report {

    private Store store;

    public ReportHR(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        List<Employee> list = store.findBy(filter);
        list.sort((t1, t2) -> (int) (t1.getSalary() - t2.getSalary()));
        for (Employee employee : list) {
            text.append("<!DOCTYPE html>")
                    .append("<title>Name; Salary</title>")
                    .append("<html>")
                    .append("<tr>")
                    .append("<th>").append(employee.getName()).append("</th>")
                    .append("<th>").append(employee.getSalary()).append("</th>")
                    .append("</html>")
                    .append("</tr>")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
