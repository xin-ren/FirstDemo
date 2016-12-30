package com.example.ysd.firstdemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.ysd.firstdemo.R;
import com.example.ysd.firstdemo.adapter.PullToRefreshAdapter;
import com.example.ysd.firstdemo.data.DataServer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 任新 on 2016/12/30 10:04.
 * Function: 下拉刷新
 * Desc:
 */
public class PullToRefreshUseActivity extends BaseActivity implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    private static final int TOTAL_COUNTER = 18;
    private static final int PAGE_SIZE = 6;
    private int delayMillis = 1000;
    private int mCurrentCounter = 0;

    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    @BindView(R.id.swipeLayout)
    SwipeRefreshLayout sl_update;

    private boolean isErr;
    private boolean mLoadMoreEndGone = false;
    private PullToRefreshAdapter mPullToRefreshAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_to_refresh_use);
        ButterKnife.bind(this);
        sl_update.setOnRefreshListener(this);
        sl_update.setColorSchemeColors(Color.rgb(47, 223, 189));
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        setTitle("Pull TO Refresh Use");
        setBackBtn();
        initAdapter();
    }

    //初始化适配器
    private void initAdapter() {
        mPullToRefreshAdapter = new PullToRefreshAdapter();
        mPullToRefreshAdapter.setOnLoadMoreListener(this);
        mPullToRefreshAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        pullToRefreshAdapter.setAutoLoadMoreSize(3);
        rv_list.setAdapter(mPullToRefreshAdapter);
        mCurrentCounter = mPullToRefreshAdapter.getData().size();

        rv_list.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
                Toast.makeText(PullToRefreshUseActivity.this, Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onRefresh() {
        mPullToRefreshAdapter.setEnableLoadMore(false);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPullToRefreshAdapter.setNewData(DataServer.getSampleData(PAGE_SIZE));
                isErr = false;
                mCurrentCounter = PAGE_SIZE;
                sl_update.setRefreshing(false);
                mPullToRefreshAdapter.setEnableLoadMore(true);
            }
        }, delayMillis);
    }

    @Override
    public void onLoadMoreRequested() {
        sl_update.setEnabled(false);
        rv_list.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mCurrentCounter >= TOTAL_COUNTER) {
//                    pullToRefreshAdapter.loadMoreEnd();//default visible
                    mPullToRefreshAdapter.loadMoreEnd(mLoadMoreEndGone);//true is gone,false is visible
                } else {
                    if (isErr) {
                        mPullToRefreshAdapter.addData(DataServer.getSampleData(PAGE_SIZE));
                        mCurrentCounter = mPullToRefreshAdapter.getData().size();
                        mPullToRefreshAdapter.loadMoreComplete();
                    } else {
                        isErr = true;
                        Toast.makeText(PullToRefreshUseActivity.this, R.string.network_err, Toast.LENGTH_LONG).show();
                        mPullToRefreshAdapter.loadMoreFail();

                    }
                }
                sl_update.setEnabled(true);
            }

        },delayMillis);
    }
}
