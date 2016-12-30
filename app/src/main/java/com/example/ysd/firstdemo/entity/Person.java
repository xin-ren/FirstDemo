package com.example.ysd.firstdemo.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.ysd.firstdemo.adapter.ExpandableItemAdapter;
/**
 * Created by 任新 on 2016/12/30 16:09.
 * Function: Person
 * Desc:
 */
public class Person implements MultiItemEntity{
    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public String name;
    public int age;

    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_PERSON;
    }
}