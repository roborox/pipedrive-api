package ru.roborox.api.pipedrive.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Deal extends HasId {
    private String title;
    private Integer stageId;
    private Long personId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("stage_id")
    public Integer getStageId() {
        return stageId;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    @JsonProperty("person_id")
    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }
}
