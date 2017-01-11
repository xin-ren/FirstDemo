package com.example.ysd.firstdemo.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

/**
 * Create by 任新 on 2017/1/3 14:01
 * Function：
 * Desc：
 */
@Entity
public class Users {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private int age;
    private boolean isBoy;

    public Users() {
    }

    public Users(Long id, String name, int age, boolean isBoy) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isBoy = isBoy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isBoy() {
        return isBoy;
    }

    public void setBoy(boolean boy) {
        isBoy = boy;
    }
}
