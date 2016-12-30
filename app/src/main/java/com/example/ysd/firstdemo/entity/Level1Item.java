package com.example.ysd.firstdemo.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.ysd.firstdemo.adapter.ExpandableItemAdapter;
/**
 * Created by 任新 on 2016/12/30 16:08.
 * Function:Level1Item
 * Desc:
 */
public class Level1Item extends AbstractExpandableItem<Person> implements MultiItemEntity{
    public String title;
    public String subTitle;

    public Level1Item(String title, String subTitle) {
        this.subTitle = subTitle;
        this.title = title;
    }

    @Override
    public int getItemType() {
        return ExpandableItemAdapter.TYPE_LEVEL_1;
    }

    @Override
    public int getLevel() {
        return 1;
    }
}