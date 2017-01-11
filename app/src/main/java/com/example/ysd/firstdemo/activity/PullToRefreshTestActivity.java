package com.example.ysd.firstdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.ysd.firstdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicDefaultHeader;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

/**
 * Created by 任新 on 2017/1/3 9:21.
 * Function: PullToRefreshTestActivity
 * Desc:
 */

public class PullToRefreshTestActivity extends AppCompatActivity {
    @BindView(R.id.store_house_ptr_frame)
    PtrFrameLayout mPtrFrame;
    @BindView(R.id.tv_test)
    TextView tv_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh_test);
        ButterKnife.bind(this);

        /**
         * 经典 风格的头部实现
         */
        final PtrClassicDefaultHeader header = new PtrClassicDefaultHeader(this);
        header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);

        /**
         * StoreHouse风格的头部实现
         */
        //final StoreHouseHeader header = new StoreHouseHeader(this);
        //header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);

        /**
         * using a string, support: A-Z 0-9 - .
         * you can add more letters by {@link in.srain.cube.views.ptr.header.StoreHousePath#addChar}
         */
        // header.initWithString("Alibaba");

        /**
         * Material Design风格的头部实现
         */
        //final MaterialHeader header = new MaterialHeader(this);
        //header.setPadding(0, PtrLocalDisplay.dp2px(15), 0, 0);//显示相关工具类，用于获取用户屏幕宽度、高度以及屏幕密度。同时提供了dp和px的转化方法。

        /**
         * Rentals Style风格的头部实现
         * 这个需要引入这两个类RentalsSunDrawable.java ; RentalsSunHeaderView.java
         * 在人家git上的daemon中能找到
         */
        //final RentalsSunHeaderView header = new RentalsSunHeaderView(this);
        //header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        //header.setPadding(0, LocalDisplay.dp2px(15), 0, LocalDisplay.dp2px(10));
        //header.setUp(mPtrFrame);
        //mPtrFrame.setLoadingMinTime(1000);
        //mPtrFrame.setDurationToCloseHeader(1500);

        mPtrFrame.setHeaderView(header);
        // mPtrFrame.setPinContent(true);//刷新时，保持内容不动，仅头部下移,默认,false
        mPtrFrame.addPtrUIHandler(header);
        //mPtrFrame.setKeepHeaderWhenRefresh(true);//刷新时保持头部的显示，默认为true
        //mPtrFrame.disableWhenHorizontalMove(true);//如果是ViewPager，设置为true，会解决ViewPager滑动冲突问题。
        mPtrFrame.setPtrHandler(new PtrHandler() {

            //需要加载数据时触发
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                System.out.println("PullToRefreshTestActivity.onRefreshBegin");
                mPtrFrame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrFrame.refreshComplete();
                        tv_test.setText("");
                        //mPtrFrame.autoRefresh();//自动刷新
                    }
                }, 1800);

            }

            /**刷新成功是否可以执行下来刷新，比如列表为空或者列表第一项在最上面时。
             */
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                System.out.println("PullToRefreshTestActivity.checkCanDoRefresh");
                // 默认实现，根据实际情况做改动
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
                // return true;
            }
        });
    }
}
