package ru.roborox.api.pipedrive;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.roborox.api.pipedrive.model.*;

import java.util.Properties;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class PipedriveApiTest {
    private PipedriveApi<Person, Deal> api;
    private String apiToken;
    private String testEmail;

    @BeforeClass
    public void setUp() throws Exception {
        final Properties props = new Properties();
        props.load(getClass().getClassLoader().getResourceAsStream("app.properties"));

        apiToken = props.getProperty("pipedriveToken");
        testEmail = props.getProperty("pipedriveTestEmail");
        api = PipedriveApi.createDefault(apiToken);
    }

    @Test
    public void findPersons() throws Exception {
        final Page<PersonId> result = api.findPersons(testEmail, 0, 100, true);
        assertEquals(result.getData().size(), 1);
        assertNotNull(result.getData().get(0).getName());
        assertEquals(result.getData().get(0).getEmail(), testEmail);
    }

    @Test
    public void crudPerson() throws Exception {
        final Person person = new Person();
        person.setName("Test Person");
        final Response<Person, Void> createResponse = api.addPerson(person);
        assertNotNull(createResponse.getData().getId());

        person.setName("Test Person2");
        person.setId(createResponse.getData().getId());
        final Response<Person, Void> updateResponse = api.updatePerson(person);
        assertEquals(updateResponse.getData().getName(), "Test Person2");

        final Response<HasId, Void> deleteResponse = api.deletePerson(createResponse.getData().getId());
        assertEquals(deleteResponse.getData().getId(), createResponse.getData().getId());
    }
}
