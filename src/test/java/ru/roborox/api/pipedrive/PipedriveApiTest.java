package ru.roborox.api.pipedrive;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.roborox.api.pipedrive.model.Deal;
import ru.roborox.api.pipedrive.model.Page;
import ru.roborox.api.pipedrive.model.Person;
import ru.roborox.api.pipedrive.model.PersonId;

import java.io.IOException;
import java.net.URISyntaxException;
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
    public void findPersons() throws IOException, URISyntaxException {
        final Page<PersonId> result = api.findPersons(testEmail, 0, 100, true);
        assertEquals(result.getData().size(), 1);
        assertNotNull(result.getData().get(0).getName());
        assertEquals(result.getData().get(0).getEmail(), testEmail);
    }
}
