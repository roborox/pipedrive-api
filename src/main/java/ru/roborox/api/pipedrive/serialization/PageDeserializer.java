package ru.roborox.api.pipedrive.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import ru.roborox.api.pipedrive.model.Page;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PageDeserializer extends AbstractDeserializer<Page> {
    private static final ThreadLocal<Class> tClass = new ThreadLocal<>();

    static void setNeededClass(Class tClass) {
        PageDeserializer.tClass.set(tClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Page deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        final TreeNode json = p.getCodec().readTree(p);
        final List<Object> data = new ArrayList<>();
        final Class tClass = PageDeserializer.tClass.get();
        final TreeNode dataNode = json.get("data");
        if (dataNode instanceof ArrayNode) {
            for (JsonNode item : ((ArrayNode) dataNode)) {
                data.add(deserialize(item, tClass));
            }
        }
        final TreeNode pagination = json.get("additional_data").get("pagination");
        return new Page(getInt(pagination.get("start")), getInt(pagination.get("limit")), getBoolean(pagination.get("more_items_in_collection")), data);
    }
}
