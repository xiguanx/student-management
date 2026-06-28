package com.example.studentmanagement.dto;

import java.util.List;

public class NameRequest {

    private List<String> name;

    public NameRequest() {}

    public NameRequest(List<String> name) {
        this.name = name;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NameRequest{name=" + name + "}";
    }
}
