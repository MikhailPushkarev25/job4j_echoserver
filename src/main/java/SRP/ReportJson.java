package SRP;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

public class ReportJson implements Report {

    private Store store;

    public ReportJson(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) throws JAXBException, IOException {
        List<Employee> list = store.findBy(filter);
        Gson gson = new GsonBuilder().create();
        return gson.toJson(list);
    }
}
