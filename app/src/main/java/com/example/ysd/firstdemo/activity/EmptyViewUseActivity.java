package com.example.ysd.firstdemo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.ysd.firstdemo.R;
import com.example.ysd.firstdemo.adapter.QuickAdapter;
import com.example.ysd.firstdemo.data.DataServer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 任新 on 2016/12/30 13:03.
 * Function: EmptyViewUseActivity
 * Desc:
 */
public class EmptyViewUseActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.fab_reset)
    FloatingActionButton fab_reset;
    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    private QuickAdapter mQuickAdapter;
    private View loadingView;
    private View notDataView;
    private View errorView;

    private boolean mError = true;
    private boolean mNoData = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBackBtn();
        setTitle("EmptyView Use");
        setContentView(R.layout.activity_empty_view_use);
        ButterKnife.bind(this);
        rv_list.setHasFixedSize(true);
        rv_list.setLayoutManager(new LinearLayoutManager(this));

        loadingView = getLayoutInflater().inflate(R.layout.loading_view, (ViewGroup) rv_list.getParent(), false);
        notDataView = getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) rv_list.getParent(), false);
        notDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
        errorView = getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) rv_list.getParent(), false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRefresh();
            }
        });
        initAdapter();
        onRefresh();
    }

    private void initAdapter() {
        mQuickAdapter = new QuickAdapter(0);
        rv_list.setAdapter(mQuickAdapter);
    }

    @Override
    public void onClick(View v) {
        mError = true;
        mNoData = true;
        mQuickAdapter.setNewData(null);
        onRefresh();
    }
    //数据刷新
    private void onRefresh() {
        mQuickAdapter.setEmptyView(loadingView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mError) {
                    mQuickAdapter.setEmptyView(errorView);
                    mError = false;
                } else {
                    if (mNoData) {
                        mQuickAdapter.setEmptyView(notDataView);
                        mNoData = false;
                    } else {
                        mQuickAdapter.setNewData(DataServer.getSampleData(10));
                    }
                }
            }
        }, 1000);
    }
}
