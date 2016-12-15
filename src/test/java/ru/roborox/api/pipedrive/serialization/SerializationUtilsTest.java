package ru.roborox.api.pipedrive.serialization;

import org.testng.annotations.Test;
import ru.roborox.api.pipedrive.model.Page;
import ru.roborox.api.pipedrive.model.Person;
import ru.roborox.api.pipedrive.model.PersonId;
import ru.roborox.api.pipedrive.model.Response;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class SerializationUtilsTest {
    @Test
    public void parseResponse() throws Exception {
        final Response<Person, Person> response = SerializationUtils.parseResponse("{\"success\":true,\"data\":{\"id\":1510,\"company_id\":1241417,\"org_id\":null,\"name\":\"Manya AWDawfawf\",\"first_name\":\"Manya\",\"last_name\":\"AWDawfawf\",\"open_deals_count\":1,\"related_open_deals_count\":0,\"closed_deals_count\":0,\"related_closed_deals_count\":0,\"participant_open_deals_count\":0,\"participant_closed_deals_count\":0,\"email_messages_count\":0,\"activities_count\":0,\"done_activities_count\":0,\"undone_activities_count\":0,\"reference_activities_count\":0,\"files_count\":0,\"notes_count\":0,\"followers_count\":1,\"won_deals_count\":0,\"related_won_deals_count\":0,\"lost_deals_count\":0,\"related_lost_deals_count\":0,\"active_flag\":true,\"phone\":[{\"value\":\"\",\"primary\":true}],\"email\":[{\"label\":\"work\",\"value\":\"testemail@go.com\",\"primary\":true}],\"first_char\":\"m\",\"update_time\":\"2016-12-12 18:40:16\",\"add_time\":\"2016-12-12 18:39:51\",\"visible_to\":\"3\",\"picture_id\":null,\"next_activity_date\":null,\"next_activity_time\":null,\"next_activity_id\":null,\"last_activity_id\":null,\"last_activity_date\":null,\"last_incoming_mail_time\":null,\"last_outgoing_mail_time\":null,\"decf5a896b05056d8c2088fb71ea35d163725481\":\"https://www.behance.net/AWDawfawfawdawd\",\"e8173f791516755ecbe005f9f372596c174315a3\":null,\"org_name\":null},\"additional_data\":{\"dropbox_email\":\"mmm@ggg\"}}", Person.class, Person.class);
        assertNotNull(response.getData().getName());
    }

    @Test
    public void parsePage() throws Exception {
        final Page<PersonId> page = SerializationUtils.parsePageResponse("{\"success\":true,\"data\":[{\"id\":1510,\"name\":\"Manya Blablabla\",\"email\":\"testemail@go.com\",\"phone\":\"\",\"org_id\":null,\"org_name\":\"\",\"visible_to\":\"3\"}],\"additional_data\":{\"search_method\":\"search_by_email\",\"pagination\":{\"start\":0,\"limit\":100,\"more_items_in_collection\":false}}}", PersonId.class);

        assertEquals(page.getLimit(), 100);
        assertEquals(page.getStart(), 0);
        assertEquals(page.getData().size(), 1);
        assertEquals(page.getData().get(0).getId(), 1510L);
    }
}