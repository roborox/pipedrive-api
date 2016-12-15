package ru.roborox.api.pipedrive;

import ru.roborox.api.pipedrive.model.Deal;
import ru.roborox.api.pipedrive.model.Person;

public class PipedriveApi<TPerson extends Person, TDeal extends Deal> {
    private final String apiUrl;
    private final Class<TPerson> personClass;
    private final Class<TDeal> dealClass;

    public PipedriveApi(String apiUrl, Class<TPerson> personClass, Class<TDeal> dealClass) {
        this.apiUrl = apiUrl;
        this.personClass = personClass;
        this.dealClass = dealClass;
    }

    public static PipedriveApi<Person, Deal> createDefault() {
        return new PipedriveApi<>(Const.API_URL, Person.class, Deal.class);
    }
}
