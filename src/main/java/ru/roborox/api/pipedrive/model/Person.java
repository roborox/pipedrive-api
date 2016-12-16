package ru.roborox.api.pipedrive.model;

import java.util.List;

public class Person extends HasId {
    private String name;
    private List<Contact> email;
    private List<Contact> phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Contact> getEmail() {
        return email;
    }

    public void setEmail(List<Contact> email) {
        this.email = email;
    }

    public List<Contact> getPhone() {
        return phone;
    }

    public void setPhone(List<Contact> phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                '}';
    }
}
