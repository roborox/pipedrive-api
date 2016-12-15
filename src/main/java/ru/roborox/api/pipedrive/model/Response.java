package ru.roborox.api.pipedrive.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ru.roborox.api.pipedrive.serialization.ResponseDeserializer;

@JsonDeserialize(using = ResponseDeserializer.class)
public class Response<D, A> {
    private final boolean success;
    private final D data;
    private final A additionalData;

    public Response(boolean success, D data, A additionalData) {
        this.success = success;
        this.data = data;
        this.additionalData = additionalData;
    }

    public boolean isSuccess() {
        return success;
    }

    public D getData() {
        return data;
    }

    public A getAdditionalData() {
        return additionalData;
    }

    @Override
    public String toString() {
        return "Response{" +
                "success=" + success +
                ", data=" + data +
                ", additionalData=" + additionalData +
                '}';
    }
}
