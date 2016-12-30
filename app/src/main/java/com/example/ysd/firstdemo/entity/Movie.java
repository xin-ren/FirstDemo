package com.example.ysd.firstdemo.entity;

/**
 * Created by 任新 on 2016/12/30 16:19.
 * Function: Movie
 * Desc:
 */
public class Movie {

    public String name;
    public int length;
    public int price;
    public String content;

    public Movie(String name, int length, int price, String content) {
        this.length = length;
        this.name = name;
        this.price = price;
        this.content = content;
    }
}
