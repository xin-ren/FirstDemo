package com.example.ysd.firstdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.ysd.firstdemo.R;
import com.example.ysd.firstdemo.adapter.MultipleItemQuickAdapter;
import com.example.ysd.firstdemo.data.DataServer;
import com.example.ysd.firstdemo.entity.MultipleItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 任新 on 2016/12/29 10:39.
 * Function: MultipleItemUseActivity
 * Desc:
 */

public class MultipleItemUseActivity extends BaseActivity {

    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_item_use);
        ButterKnife.bind(this);
        setTitle("MultipleItem Use");
        setBackBtn();
        final List<MultipleItem> data = DataServer.getMultipleItemData();
        final MultipleItemQuickAdapter multipleItemAdapter = new MultipleItemQuickAdapter(this, data);
        final GridLayoutManager manager = new GridLayoutManager(this, 4);
        rv_list.setLayoutManager(manager);
        multipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return data.get(position).getSpanSize();
            }
        });
        rv_list.setAdapter(multipleItemAdapter);
    }
}
