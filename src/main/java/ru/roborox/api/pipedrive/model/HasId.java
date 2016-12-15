package ru.roborox.api.pipedrive.model;

public class HasId {
    private Long id;

    public HasId() {
    }

    public HasId(Long id) {

        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "HasId{" +
                "id=" + id +
                '}';
    }
}
