package ru.roborox.api.pipedrive;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.impl.client.HttpClients;
import ru.roborox.api.pipedrive.model.Deal;
import ru.roborox.api.pipedrive.model.Page;
import ru.roborox.api.pipedrive.model.Person;

import java.io.IOException;
import java.util.List;

public class PipedriveApi<TPerson extends Person, TDeal extends Deal> {
    private final String apiToken;
    private final String apiUrl;
    private final Class<TPerson> personClass;
    private final Class<TDeal> dealClass;

    private final Executor executor;
    private final ObjectMapper objectMapper;

    public PipedriveApi(String apiToken, String apiUrl, Class<TPerson> personClass, Class<TDeal> dealClass) {
        this.apiToken = apiToken;
        this.apiUrl = apiUrl;
        this.personClass = personClass;
        this.dealClass = dealClass;
        this.executor = Executor.newInstance(HttpClients.createDefault());
        this.objectMapper = new ObjectMapper();
    }

    public static PipedriveApi<Person, Deal> createDefault(String apiToken) {
        return new PipedriveApi<>(apiToken, Const.API_URL, Person.class, Deal.class);
    }

    public Page<TPerson> find(String term, int start, Integer limit, boolean searchByEmail) {
        return null;
    }

    private <T> T executeForObject(Request request, Class<T> tClass) throws IOException {
        return objectMapper.readValue(executor.execute(request).returnContent().asString(), tClass);
    }
}
