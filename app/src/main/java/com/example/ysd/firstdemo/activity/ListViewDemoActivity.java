package com.example.ysd.firstdemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ysd.firstdemo.Engine;
import com.example.ysd.firstdemo.R;
import com.example.ysd.firstdemo.TestApplication;
import com.example.ysd.firstdemo.entity.BannerModel;
import com.example.ysd.firstdemo.entity.RefreshModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.androidcommon.adapter.BGAAdapterViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import cn.bingoogolapple.bgabanner.BGABanner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Create by 任新 on 2016/12/27 15:55
 * Function：ListView测试界面
 * Desc：
 */
public class ListViewDemoActivity extends AppCompatActivity implements BGABanner.Adapter<ImageView,String>,BGABanner.Delegate<ImageView,String>{

    @BindView(R.id.lv_content)
    ListView lv_content;

    private BGABanner mBanner;
    private ContentAdapter mContentAdapter;
    private Engine mEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_demo);
        ButterKnife.bind(this);
        mEngine = TestApplication.getInstance().getEngine();
        initListView();
        loadBannerData();
        loadContentData();
    }

    /**
     * 初始化ListView
     */
    private void initListView() {
        // 初始化HeaderView
        View headerView = View.inflate(this, R.layout.layout_header, null);
        mBanner = (BGABanner) headerView.findViewById(R.id.banner);
        mBanner.setAdapter(this);
        mBanner.setDelegate(this);

        // 初始化ListView
        lv_content.addHeaderView(headerView);
        mContentAdapter = new ContentAdapter(this);
        lv_content.setAdapter(mContentAdapter);
    }

    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
        Glide.with(this)
                .load(model)
                .placeholder(R.drawable.holder)
                .error(R.drawable.holder)
                .dontAnimate()
                .centerCrop()
                .into(itemView);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
        Toast.makeText(this, "点击了第" + (position + 1) + "页", Toast.LENGTH_SHORT).show();
    }

    /**
     * 加载头部广告条的数据
     */
    private void loadBannerData() {
        mEngine.fetchItemsWithItemCount(5).enqueue(new Callback<BannerModel>() {
            @Override
            public void onResponse(Call<BannerModel> call, Response<BannerModel> response) {
                BannerModel bannerModel = response.body();
                mBanner.setData(bannerModel.imgs, bannerModel.tips);
            }

            @Override
            public void onFailure(Call<BannerModel> call, Throwable t) {
                Toast.makeText(TestApplication.getInstance(), "加载广告条数据失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 加载内容列表数据
     */
    private void loadContentData() {
        mEngine.loadContentData("http://7xk9dj.com1.z0.glb.clouddn.com/refreshlayout/api/defaultdata.json").enqueue(new Callback<List<RefreshModel>>() {
            @Override
            public void onResponse(Call<List<RefreshModel>> call, Response<List<RefreshModel>> response) {
                mContentAdapter.setData(response.body());
            }

            @Override
            public void onFailure(Call<List<RefreshModel>> call, Throwable t) {
                Toast.makeText(TestApplication.getInstance(), "加载内容数据失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class ContentAdapter extends BGAAdapterViewAdapter<RefreshModel> {

        public ContentAdapter(Context context) {
            super(context, R.layout.item_normal);
        }

        @Override
        protected void fillData(BGAViewHolderHelper helper, int position, RefreshModel model) {
            helper.setText(R.id.tv_item_normal_title, model.title).setText(R.id.tv_item_normal_detail, model.detail);
        }
    }
}
