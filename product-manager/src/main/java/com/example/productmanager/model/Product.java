package com.example.productmanager.model;

import java.time.LocalDate;

public class Product {
    private long id;
    private String name;
    private String description;
    private LocalDate createAt;
    private LocalDate deleteAt;
    private float price;
    private long idCategory;

    public Product() {
    }
    public Product(long id, String name, String description, LocalDate createAt, LocalDate deleteAt, float price, long idCategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createAt = createAt;
        this.deleteAt = deleteAt;
        this.price = price;
        this.idCategory = idCategory;
    }

    public Product(String name, String description, float price, long idCategory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.idCategory = idCategory;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public LocalDate getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(LocalDate deleteAt) {
        this.deleteAt = deleteAt;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(long idCategory) {
        this.idCategory = idCategory;
    }
}
