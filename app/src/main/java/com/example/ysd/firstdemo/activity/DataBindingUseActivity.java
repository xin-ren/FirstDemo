package com.example.ysd.firstdemo.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ysd.firstdemo.R;
import com.example.ysd.firstdemo.adapter.DataBindingUseAdapter;
import com.example.ysd.firstdemo.entity.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 任新 on 2016/12/30 16:16.
 * Function: DataBindingUseActivity
 * Desc:
 */
public class DataBindingUseActivity extends BaseActivity {

    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    private DataBindingUseAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBackBtn();
        setTitle("DataBinding Use");
        setContentView(R.layout.activity_data_binding_use);
        ButterKnife.bind(this);
        mAdapter = new DataBindingUseAdapter(R.layout.item_movie, genData());
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        rv_list.setAdapter(mAdapter);
    }

    //创建数据
    private List<Movie> genData() {
        ArrayList<Movie> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            String name = "Hoteis in Rio de Janeiro";
            int price = random.nextInt(10) + 10;
            int len = random.nextInt(80) + 60;
            Movie movie = new Movie(name, len, price,"He was one of Australia's most distinguished artistes");
            list.add(movie);
        }
        return list;
    }
}
