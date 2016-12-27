package com.example.ysd.firstdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.ysd.firstdemo.R;
import com.example.ysd.firstdemo.adapter.HeaderAndFooterAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 任新 on 2016/12/29 10:40.
 * Function: HeaderAndFooterUseActivity
 * Desc:
 */
public class HeaderAndFooterUseActivity extends BaseActivity {
    private static final int PAGE_SIZE = 3;
    @BindView(R.id.rv_list)
    RecyclerView rv_list;
    private HeaderAndFooterAdapter headerAndFooterAdapter;
    private View footerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBackBtn();
        setTitle("HeaderAndFooter Use");
//        Window window = this.getWindow();
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        //设置状态栏颜色
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.setStatusBarColor(Color.TRANSPARENT);
//        }
        setContentView(R.layout.activity_header_and_footer_use);
        ButterKnife.bind(this);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        initAdapter();


        View headerView = getView(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerAndFooterAdapter.addHeaderView(getView(getRemoveHeaderListener(), "click me to remove me"), 0);
            }
        }, "click me to add new header");
        headerAndFooterAdapter.addHeaderView(headerView);

        footerView = getView(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerAndFooterAdapter.addFooterView(getView(getRemoveFooterListener(), "click me to remove me"));
            }
        }, "click me to add new footer");
        footerView = getLayoutInflater().inflate(R.layout.footer_view, (ViewGroup) rv_list.getParent(), false);
        headerAndFooterAdapter.addFooterView(footerView, 0);

        rv_list.setAdapter(headerAndFooterAdapter);
    }

    private View getView(View.OnClickListener listener, String text) {
        View view = getLayoutInflater().inflate(R.layout.head_view, (ViewGroup) rv_list.getParent(), false);
        view.setOnClickListener(listener);
        return view;
    }

    //removeHeaderView()
    private View.OnClickListener getRemoveHeaderListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerAndFooterAdapter.removeHeaderView(v);
            }
        };
    }

    //removeFooterView()
    private View.OnClickListener getRemoveFooterListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headerAndFooterAdapter.removeFooterView(v);
            }
        };
    }

    //初始化适配器
    private void initAdapter() {
        headerAndFooterAdapter = new HeaderAndFooterAdapter(PAGE_SIZE);
        headerAndFooterAdapter.openLoadAnimation();
        rv_list.setAdapter(headerAndFooterAdapter);
        rv_list.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(HeaderAndFooterUseActivity.this, "" + Integer.toString(position), Toast.LENGTH_LONG).show();
            }
        });

    }

}
