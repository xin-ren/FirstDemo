package com.example.ysd.firstdemo.entity;

/**
 * Create by 任新 on 2016/12/26 14:01
 * Function：控制实体类
 * Desc：
 */
public class Contributor {
    private String login;
    private Integer contributions;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getContributions() {
        return contributions;
    }

    public void setContributions(Integer contributions) {
        this.contributions = contributions;
    }
}
