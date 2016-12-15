package ru.roborox.api.pipedrive;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClients;
import ru.roborox.api.pipedrive.model.*;
import ru.roborox.api.pipedrive.serialization.SerializationUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.function.Function;

public class PipedriveApi<TPerson extends Person, TDeal extends Deal> {
    public static final String FIND = "/find";

    public static final String PERSONS = "/persons";
    public static final String DEALS = "/deals";

    private final String apiToken;
    private final String apiUrl;
    private final Class<TPerson> personClass;
    private final Class<TDeal> dealClass;

    private final Executor executor;

    public PipedriveApi(String apiToken, String apiUrl, Class<TPerson> personClass, Class<TDeal> dealClass) {
        this.apiToken = apiToken;
        this.apiUrl = apiUrl;
        this.personClass = personClass;
        this.dealClass = dealClass;
        this.executor = Executor.newInstance(HttpClients.createDefault());
    }

    public static PipedriveApi<Person, Deal> createDefault(String apiToken) {
        return new PipedriveApi<>(apiToken, Const.API_URL, Person.class, Deal.class);
    }

    public Page<PersonId> findPersons(String term, int start, int limit, boolean searchByEmail) throws IOException, URISyntaxException {
        return getForPage(PERSONS + FIND, PersonId.class, Pair.of("term", term), Pair.of("start", start), Pair.of("limit", limit), Pair.of("search_by_email", searchByEmail));
    }

    public Response<HasId, Void> addPerson(TPerson person) throws IOException, URISyntaxException {
        return post(PERSONS, person, HasId.class, Void.class);
    }

    public Response<HasId, Void> updatePerson(TPerson person) throws IOException, URISyntaxException {
        return put(PERSONS + "/" + person.getId(), person, HasId.class, Void.class);
    }

    public Response<HasId, Void> deletePerson(long id) throws IOException, URISyntaxException {
        return request(Request::Delete, PERSONS + "/" + id, null, HasId.class, Void.class);
    }

    public Response<HasId, Void> addDeal(TDeal deal) throws IOException, URISyntaxException {
        return post(DEALS, deal, HasId.class, Void.class);
    }

    public Response<HasId, Void> deleteDeal(long id) throws IOException, URISyntaxException {
        return request(Request::Delete, DEALS + "/" + id, null, HasId.class, Void.class);
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

    private <D, A> Response<D, A> post(String path, Object request, Class<D> dClass, Class<A> aClass) throws IOException, URISyntaxException {
        return request(Request::Post, path, request, dClass, aClass);
    }

    private <D, A> Response<D, A> put(String path, Object request, Class<D> dClass, Class<A> aClass) throws IOException, URISyntaxException {
        return request(Request::Put, path, request, dClass, aClass);
    }

    private <D, A> Response<D, A> request(Function<String, Request> createRequestFunction, String path, Object request, Class<D> dClass, Class<A> aClass) throws IOException, URISyntaxException {
        final Request req = createRequestFunction.apply(getUrl(path));
        if (request != null) {
            req.bodyString(SerializationUtils.toString(request), ContentType.APPLICATION_JSON);
        }
        return SerializationUtils.parseResponse(executor.execute(req).returnContent().asString(), dClass, aClass);
    }
}
