package com.example.ysd.firstdemo.entity;


/**
 * Create by 任新 on 2016/12/26 14:01
 * Function：item实体类
 * Desc：
 */
public class Item {

    private String name;
    private String full_name;
    private String description;
    private Owner owner;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Owner getOwner() {
        return owner;
    }
}