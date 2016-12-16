package ru.roborox.api.pipedrive.model;

public class Contact {
    private String value;
    private boolean primary = true;

    public Contact() {
    }

    public Contact(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }
}
