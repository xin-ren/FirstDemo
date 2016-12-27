package com.example.ysd.firstdemo.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.ysd.firstdemo.R;
import com.example.ysd.firstdemo.TestApplication;
import com.example.ysd.firstdemo.entity.BannerModel;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Create by 任新 on 2016/12/27 15:55
 * Function：fresco测试界面
 * Desc：
 */
public class FrescoDemoActivity extends AppCompatActivity {

    @BindView(R.id.banner_fresco_demo_content)
    BGABanner banner_FrescoDemo_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco_demo);
        ButterKnife.bind(this);

        banner_FrescoDemo_content.setDelegate(new BGABanner.Delegate<SimpleDraweeView, String>() {
            @Override
            public void onBannerItemClick(BGABanner banner, SimpleDraweeView itemView, String model, int position) {
                Toast.makeText(banner.getContext(), "点击了第" + (position + 1) + "页", Toast.LENGTH_SHORT).show();
            }
        });
        banner_FrescoDemo_content.setAdapter(new BGABanner.Adapter<SimpleDraweeView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, SimpleDraweeView itemView, String model, int position) {
                itemView.setImageURI(Uri.parse(model));
            }
        });

        TestApplication.getInstance().getEngine().fetchItemsWithItemCount(5).enqueue(new Callback<BannerModel>() {
            @Override
            public void onResponse(Call<BannerModel> call, Response<BannerModel> response) {
                BannerModel bannerModel = response.body();
                banner_FrescoDemo_content.setData(R.layout.item_fresco, bannerModel.imgs, bannerModel.tips);
            }

            @Override
            public void onFailure(Call<BannerModel> call, Throwable t) {
                Toast.makeText(TestApplication.getInstance(), "网络数据加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
