package ru.roborox.api.pipedrive.serialization;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.roborox.api.pipedrive.model.Page;
import ru.roborox.api.pipedrive.model.Response;

import java.io.IOException;

public class SerializationUtils {
    static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
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
}
