package ru.roborox.api.pipedrive.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.roborox.api.pipedrive.model.Page;
import ru.roborox.api.pipedrive.model.Response;

import java.io.IOException;

public class SerializationUtils {
    static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @SuppressWarnings("unchecked")
    public static <D, A> Response<D, A> parseResponse(String json, Class<D> dClass, Class<A> aClass) throws IOException {
        ResponseDeserializer.setNeededClasses(dClass, aClass);
        return objectMapper.readValue(json, Response.class);
    }

    @SuppressWarnings("unchecked")
    public static <T> Page<T> parsePageResponse(String json, Class<T> tClass) throws IOException {
        PageDeserializer.setNeededClass(tClass);
        return objectMapper.readValue(json, Page.class);
    }

    public static String toString(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}
