package com.example.productmanager.model;

import java.time.LocalDate;

public class Category {
    private long id;
    private String name;
    private LocalDate deleteAt;

    public Category() {
    }

    public Category(long id, String name, LocalDate deleteAt) {
        this.id = id;
        this.name = name;
        this.deleteAt = deleteAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(LocalDate deleteAt) {
        this.deleteAt = deleteAt;
    }
}
