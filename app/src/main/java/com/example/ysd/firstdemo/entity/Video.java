package com.example.ysd.firstdemo.entity;

/**
 * Created by 任新 on 2016/12/29 13:24.
 * Function:
 * Desc:
 */
public class Video {
    private String img;
    private String name;

    public Video(String img, String name) {
        this.img = img;
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
