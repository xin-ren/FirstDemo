package com.example.ysd.firstdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.ysd.firstdemo.R;
import com.example.ysd.firstdemo.adapter.HomeAdapter;
import com.example.ysd.firstdemo.entity.HomeItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 任新 on 2016/12/29 10:19.
 * Function:RecyclerView测试界面
 * Desc:
 */

public class RecyclerViewTestActivity extends AppCompatActivity {
    private static final Class<?>[] ACTIVITY = {AnimationUseActivity.class, MultipleItemUseActivity.class, HeaderAndFooterUseActivity.class};
    // PullToRefreshUseActivity.class, SectionUseActivity.class, EmptyViewUseActivity.class, ItemDragAndSwipeUseActivity.class,ItemClickActivity.class,
// ExpandableUseActivity.class, DataBindingUseActivity.class};
    private static final String[] TITLE = {"Animation", "MultipleItem", "Header/Footer"};
    //        "PullToRefresh", "Section", "EmptyView", "DragAndSwipe", "ItemClick","ExpandableItem", "DataBinding"};
    private static final int[] IMG = {R.drawable.gv_animation, R.drawable.gv_multipleltem, R.drawable.gv_header_and_footer};
// R.mipmap.gv_pulltorefresh,R.mipmap.gv_section,R.mipmap.gv_empty,R.mipmap.gv_drag_and_swipe,R.mipmap.gv_item_click,
// R.mipmap.gv_expandable,R.mipmap.gv_databinding,};

    @BindView(R.id.rv_test_recyclerViewTest)
    RecyclerView rv_test_recyclerViewTest;

    private ArrayList<HomeItem> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_test);
        ButterKnife.bind(this);
        rv_test_recyclerViewTest.setLayoutManager(new GridLayoutManager(this,2));
        initData();
        initAdapter();
    }

    //初始化适配器
    private void initAdapter() {
        BaseQuickAdapter homeAdapter = new HomeAdapter(R.layout.home_item_view, mDataList);
        homeAdapter.openLoadAnimation();
        View top = getLayoutInflater().inflate(R.layout.top_view, null);
        homeAdapter.addHeaderView(top);
        rv_test_recyclerViewTest.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(RecyclerViewTestActivity.this, ACTIVITY[position]);
                startActivity(intent);
            }
        });

        rv_test_recyclerViewTest.setAdapter(homeAdapter);
    }

    //初始化数据
    private void initData() {
        mDataList = new ArrayList<>();
        for (int i = 0; i < TITLE.length; i++) {
            HomeItem item = new HomeItem();
            item.setTitle(TITLE[i]);
            item.setActivity(ACTIVITY[i]);
            item.setImageResource(IMG[i]);
            mDataList.add(item);
        }
    }
}
