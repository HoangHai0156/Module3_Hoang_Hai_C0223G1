package com.example.productmanager.model;

import java.time.LocalDate;

public class User {
    private long id;
    private String fullName;
    private String address;
    private LocalDate dob;

    private LocalDate deleteAt;


    private ERole role;

    public User() {
    }

    public User(long id, String fullName, String address, LocalDate dob, LocalDate deleteAt, ERole role) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.dob = dob;
        this.deleteAt = deleteAt;
        this.role = role;
    }

    public User(String fullName, String address, LocalDate dob, ERole role) {
        this.fullName = fullName;
        this.address = address;
        this.dob = dob;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDate getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(LocalDate deleteAt) {
        this.deleteAt = deleteAt;
    }

    public ERole getRole() {
        return role;
    }

    public void setRole(ERole role) {
        this.role = role;
    }
}
