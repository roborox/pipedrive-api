package ru.roborox.api.pipedrive;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import ru.roborox.api.pipedrive.model.Deal;
import ru.roborox.api.pipedrive.model.Page;
import ru.roborox.api.pipedrive.model.Person;
import ru.roborox.api.pipedrive.model.PersonId;
import ru.roborox.api.pipedrive.serialization.SerializationUtils;

import java.io.IOException;
import java.net.URISyntaxException;

public class PipedriveApi<TPerson extends Person, TDeal extends Deal> {
    public static final String PERSONS_FIND = "/persons/find";

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

    public Page<PersonId> findPersons(String term, int start, int limit, boolean searchByEmail) throws IOException, URISyntaxException {
        return getForPage(PERSONS_FIND, PersonId.class, Pair.of("term", term), Pair.of("start", start), Pair.of("limit", limit), Pair.of("search_by_email", searchByEmail));
    }

    @SafeVarargs
    private final <T> Page<T> getForPage(String path, Class<T> tClass, Pair<String, Object>... params) throws URISyntaxException, IOException {
        final String url = getUrl(path, params);
        return SerializationUtils.parsePageResponse(executor.execute(Request.Get(url)).returnContent().asString(), tClass);
    }

    @SafeVarargs
    private final String getUrl(String path, Pair<String, Object>... params) throws URISyntaxException {
        final URIBuilder b = new URIBuilder(apiUrl + path);
        for (Pair<String, Object> param : params) {
            if (param.getValue() instanceof Boolean) {
                b.addParameter(param.getKey(), ((Boolean) param.getValue()) ? "1" : "0");
            } else {
                b.addParameter(param.getKey(), param.getValue().toString());
            }
        }
        b.addParameter("api_token", apiToken);
        return b.toString();
    }

    private <T> T executeForObject(Request request, Class<T> tClass) throws IOException {
        return objectMapper.readValue(executor.execute(request).returnContent().asString(), tClass);
    }
}
