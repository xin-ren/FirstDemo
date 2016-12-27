package com.example.ysd.firstdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ysd.firstdemo.event.FirstEvent;
import com.example.ysd.firstdemo.event.SecondEvent;
import com.example.ysd.firstdemo.event.ThirdEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 * Create by 任新 on 2016/12/27 11:35
 * Function：EventBus测试页面
 * Desc：
 */
public class EventBusTestActivity extends AppCompatActivity {

    @BindView(R.id.btn_first_eventBusTestActivity)
    Button btn_first_eventBusTestActivity;
    @BindView(R.id.btn_second_eventBusTestActivity)
    Button btn_second_eventBusTestActivity;
    @BindView(R.id.btn_third_eventBusTestActivity)
    Button btn_third_eventBusTestActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_test);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_first_eventBusTestActivity, R.id.btn_second_eventBusTestActivity, R.id.btn_third_eventBusTestActivity})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_first_eventBusTestActivity:
                EventBus.getDefault().post(new FirstEvent("FirstEvent btn isClicked"));
                break;
            case R.id.btn_second_eventBusTestActivity:
                EventBus.getDefault().post(new SecondEvent("SecondEvent btn isClicked"));
                break;
            case R.id.btn_third_eventBusTestActivity:
                EventBus.getDefault().post(new ThirdEvent("ThirdEvent btn isClicked"));
                break;
        }
    }
}
