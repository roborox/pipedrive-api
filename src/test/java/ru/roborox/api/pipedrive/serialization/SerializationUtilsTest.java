package ru.roborox.api.pipedrive.serialization;

import org.testng.annotations.Test;
import ru.roborox.api.pipedrive.model.*;

import java.io.IOException;

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
        assertEquals(page.getData().get(0).getId().longValue(), 1510L);
    }

    @Test
    public void parseDeal() throws IOException {
        final Deal deal = SerializationUtils.objectMapper.readValue("{\"id\":917,\"creator_user_id\":{\"id\":1770994,\"name\":\"Sticker.Place\",\"email\":\"sticker.place.mail@gmail.com\",\"has_pic\":false,\"pic_hash\":null,\"active_flag\":true,\"value\":1770994},\"user_id\":{\"id\":1770994,\"name\":\"Sticker.Place\",\"email\":\"sticker.place.mail@gmail.com\",\"has_pic\":false,\"pic_hash\":null,\"active_flag\":true,\"value\":1770994},\"person_id\":{\"name\":\"Test Person2\",\"email\":[{\"value\":\"\",\"primary\":true}],\"phone\":[{\"value\":\"\",\"primary\":true}],\"value\":1523},\"org_id\":null,\"stage_id\":10,\"title\":\"Test Person2\",\"value\":0,\"currency\":\"RUB\",\"add_time\":\"2016-12-15 11:54:35\",\"update_time\":\"2016-12-15 11:54:35\",\"stage_change_time\":null,\"active\":true,\"deleted\":false,\"status\":\"open\",\"next_activity_date\":null,\"next_activity_time\":null,\"next_activity_id\":null,\"last_activity_id\":null,\"last_activity_date\":null,\"lost_reason\":null,\"visible_to\":\"3\",\"close_time\":null,\"pipeline_id\":1,\"won_time\":null,\"first_won_time\":null,\"lost_time\":null,\"products_count\":0,\"files_count\":0,\"notes_count\":0,\"followers_count\":0,\"email_messages_count\":0,\"activities_count\":0,\"done_activities_count\":0,\"undone_activities_count\":0,\"reference_activities_count\":0,\"participants_count\":0,\"expected_close_date\":null,\"last_incoming_mail_time\":null,\"last_outgoing_mail_time\":null,\"2bb68a1187d0e4a0d018cca7affabc14454bf3d4\":null,\"59dfe81df1702bf87fd2f1fc0c868a03e5244a3b\":null,\"50d1143d4821a9250b5a7a6492c52dc7cd3e0926\":null,\"62c277db32c18b93e8dd8ca58399e738f331b623\":null,\"d79e672056194607e12d3e55eb8348a404b8cc36\":null,\"a54842a93b3f88f478b8b4ce6e09f2ad27b31d8a\":null,\"stage_order_nr\":2,\"person_name\":\"Test Person2\",\"org_name\":null,\"next_activity_subject\":null,\"next_activity_type\":null,\"next_activity_duration\":null,\"next_activity_note\":null,\"formatted_value\":\"RUB0\",\"rotten_time\":null,\"weighted_value\":0,\"formatted_weighted_value\":\"RUB0\",\"owner_name\":\"Sticker.Place\",\"cc_email\":\"stickerplace+deal917@pipedrivemail.com\",\"org_hidden\":false,\"person_hidden\":false}", Deal.class);

    }
}