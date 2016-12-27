package com.example.ysd.firstdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ysd.firstdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * Create by 任新 on 2016/12/28 10:20
 * Function：FlycoTabLayout测试页面
 * Desc：
 */
public class FlycoTabLayoutTestActivity extends AppCompatActivity {

    @BindView(R.id.btn_common_flycoTabLayoutActivity)
    Button btn_common_flycoTabLayoutActivity;
    @BindView(R.id.btn_segment_flycoTabLayoutActivity)
    Button btn_segment_flycoTabLayoutActivity;
    @BindView(R.id.btn_sliding_flycoTabLayoutActivity)
    Button btn_sliding_flycoTabLayoutActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flyco_tab_layout_test);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_common_flycoTabLayoutActivity, R.id.btn_segment_flycoTabLayoutActivity, R.id.btn_sliding_flycoTabLayoutActivity})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_common_flycoTabLayoutActivity:
                startActivity(new Intent(FlycoTabLayoutTestActivity.this,CommonTabActivity.class));
                break;
            case R.id.btn_segment_flycoTabLayoutActivity:
                startActivity(new Intent(FlycoTabLayoutTestActivity.this,SegmentTabActivity.class));
                break;
            case R.id.btn_sliding_flycoTabLayoutActivity:
                startActivity(new Intent(FlycoTabLayoutTestActivity.this,SlidingTabActivity.class));
                break;
        }
    }
}
