package ru.roborox.api.pipedrive.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ru.roborox.api.pipedrive.serialization.PageDeserializer;

import java.util.List;

@JsonDeserialize(using = PageDeserializer.class)
public class Page<T> {
    private final int start;
    private final int limit;
    private final boolean moreItemsInCollection;
    private List<T> data;

    public Page(int start, int limit, boolean moreItemsInCollection, List<T> data) {
        this.start = start;
        this.limit = limit;
        this.moreItemsInCollection = moreItemsInCollection;
        this.data = data;
    }

    public int getStart() {
        return start;
    }

    public int getLimit() {
        return limit;
    }

    public boolean isMoreItemsInCollection() {
        return moreItemsInCollection;
    }

    public List<T> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Page{" +
                "start=" + start +
                ", limit=" + limit +
                ", moreItemsInCollection=" + moreItemsInCollection +
                ", data=" + data +
                '}';
    }
}
