package com.example.ysd.firstdemo.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ysd.firstdemo.R;
import com.example.ysd.firstdemo.entity.HomeItem;

import java.util.List;

/**
 * Created by 任新 on 2016/12/29 10:48.
 * Function: HomeAdapter
 * Desc:
 */
public class HomeAdapter extends BaseQuickAdapter<HomeItem, BaseViewHolder> {
    public HomeAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeItem item) {
        helper.setText(R.id.text, item.getTitle());
        helper.setImageResource(R.id.icon, item.getImageResource());

    }
}
