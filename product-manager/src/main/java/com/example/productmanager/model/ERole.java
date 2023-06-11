package com.example.productmanager.model;

import java.util.List;

public enum ERole {
    ADMIN(1,"admin"), USER(2,"customer");
    private long id;
    private String name;
    ERole(long id, String name){
        this.id = id;
        this.name = name;
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
    public static boolean isRoleExist(String role){
        for (ERole eRole: ERole.values()){
            if (eRole.name().equals(role)){
                return true;
            }
        }
        return false;
    }
}
