package ru.roborox.api.pipedrive.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.node.BooleanNode;
import ru.roborox.api.pipedrive.model.Response;

import java.io.IOException;

public class ResponseDeserializer extends AbstractDeserializer<Response> {
    private static final ThreadLocal<Class> dataClass = new ThreadLocal<>();
    private static final ThreadLocal<Class> additionalDataClass = new ThreadLocal<>();

    static void setNeededClasses(Class dClass, Class aClass) {
        dataClass.set(dClass);
        additionalDataClass.set(aClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Response deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
        final TreeNode json = p.getCodec().readTree(p);
        final Object data = deserialize(json.get("data"), dataClass.get());
        final Object additionalData = deserialize(json.get("additional_data"), additionalDataClass.get());
        return new Response(((BooleanNode) json.get("success")).asBoolean(), data, additionalData);
    }
}
