package com.example.ysd.firstdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.ysd.firstdemo.R;
import com.example.ysd.firstdemo.adapter.ItemClickAdapter;
import com.example.ysd.firstdemo.entity.ClickEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 任新 on 2016/12/30 14:28.
 * Function: ItemClickActivity
 * Desc:
 */

public class ItemClickActivity extends BaseActivity {
    private static final int PAGE_SIZE = 10;
    private static String TAG = "RecyclerClickItemActivity";

    @BindView(R.id.rl_list)
    RecyclerView rl_list;

    private ItemClickAdapter itemClickAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_click);
        ButterKnife.bind(this);
        setBackBtn();
        setTitle("ItemClickActivity Activity");
        setContentView(R.layout.activity_item_click);
        rl_list.setLayoutManager(new LinearLayoutManager(this));
        initAdapter();
        rl_list.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                Log.d(TAG, "SimpleOnItemClick: ");
            }
        });
    }

    //初始化适配器
    private void initAdapter() {
        List<ClickEntity> data = new ArrayList<>();
        data.add(new ClickEntity(ClickEntity.CLICK_ITEM_VIEW));
        data.add(new ClickEntity(ClickEntity.CLICK_ITEM_CHILD_VIEW));
        data.add(new ClickEntity(ClickEntity.LONG_CLICK_ITEM_VIEW));
        data.add(new ClickEntity(ClickEntity.LONG_CLICK_ITEM_CHILD_VIEW));
        itemClickAdapter = new ItemClickAdapter(data);
        itemClickAdapter.openLoadAnimation();
        rl_list.setAdapter(itemClickAdapter);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
