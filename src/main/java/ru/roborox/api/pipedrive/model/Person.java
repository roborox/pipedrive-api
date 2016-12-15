package ru.roborox.api.pipedrive.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person extends HasId {
    private String name;
    private String link;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("e8173f791516755ecbe005f9f372596c174315a3")
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                '}';
    }
}
