package com.example.ysd.firstdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.ysd.firstdemo.R;
import com.example.ysd.firstdemo.adapter.AnimationAdapter;
import com.example.ysd.firstdemo.animotion.CustomAnimation;
import com.example.ysd.firstdemo.entity.Status;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.kyleduo.switchbutton.SwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 任新 on 2016/12/29 10:39.
 * Function: AnimationUseActivity
 * Desc:
 */
public class AnimationUseActivity extends AppCompatActivity {

    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.ms_spinner)
    MaterialSpinner ms_spinner;
    @BindView(R.id.sBtn_switch)
    SwitchButton sBtn_switch;
    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    private AnimationAdapter animationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_use);
        ButterKnife.bind(this);
        rv_list.setHasFixedSize(true);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        initAdapter();
        initMenu();
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }

    //初始化适配器
    private void initAdapter() {
        animationAdapter = new AnimationAdapter();
        animationAdapter.openLoadAnimation();
        rv_list.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                String content = null;
                Status status = (Status) adapter.getItem(position);
                switch (view.getId()) {
                    case R.id.img:
                        content = "img:" + status.getUserAvatar();
                        Toast.makeText(AnimationUseActivity.this, content, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.tweetName:
                        content = "name:" + status.getUserName();
                        Toast.makeText(AnimationUseActivity.this, content, Toast.LENGTH_LONG).show();
                        break;
                    case R.id.tweetText:
                        // you have set clickspan .so there should not solve any click event ,just empty
                        break;
                }

            }
        });
        rv_list.setAdapter(animationAdapter);
    }

    //初始化menu
    private void initMenu() {
        ms_spinner.setItems("AlphaIn", "ScaleIn", "SlideInBottom", "SlideInLeft", "SlideInRight", "Custom");
        ms_spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                switch (position) {
                    case 0:
                        animationAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
                        break;
                    case 1:
                        animationAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
                        break;
                    case 2:
                        animationAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
                        break;
                    case 3:
                        animationAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                        break;
                    case 4:
                        animationAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
                        break;
                    case 5:
                        animationAdapter.openLoadAnimation(new CustomAnimation());
                        break;
                    default:
                        break;
                }
                rv_list.setAdapter(animationAdapter);
            }
        });

        sBtn_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final CompoundButton buttonView, final boolean isChecked) {
                if (isChecked) {
                    animationAdapter.isFirstOnly(true);
                } else {
                    animationAdapter.isFirstOnly(false);
                }
                animationAdapter.notifyDataSetChanged();
            }
        });

    }
}
