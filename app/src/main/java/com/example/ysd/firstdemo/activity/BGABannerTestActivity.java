package com.example.ysd.firstdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ysd.firstdemo.Engine;
import com.example.ysd.firstdemo.R;
import com.example.ysd.firstdemo.TestApplication;
import com.example.ysd.firstdemo.entity.BannerModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.transformer.TransitionEffect;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Create by 任新 on 2016/12/27 14:21
 * Function：BGABanner测试页面
 * Desc：
 */
public class BGABannerTestActivity extends AppCompatActivity implements BGABanner.Delegate<ImageView, String>, BGABanner.Adapter<ImageView, String>{

    @BindView(R.id.banner_main_default)
    BGABanner banner_mainDefault;
    @BindView(R.id.banner_main_cube)
    BGABanner banner_mainCube;
    @BindView(R.id.banner_main_accordion)
    BGABanner banner_mainAccordion;
    @BindView(R.id.banner_main_flip)
    BGABanner banner_mainFlip;
    @BindView(R.id.banner_main_rotate)
    BGABanner banner_mainRotate;
    @BindView(R.id.banner_main_alpha)
    BGABanner banner_mainAlpha;
    @BindView(R.id.banner_main_zoomFade)
    BGABanner banner_mainZoomFade;
    @BindView(R.id.banner_main_fade)
    BGABanner banner_mainFade;
    @BindView(R.id.banner_main_zoomCenter)
    BGABanner banner_mainZoomCenter;
    @BindView(R.id.banner_main_zoom)
    BGABanner banner_mainZoom;
    @BindView(R.id.banner_main_stack)
    BGABanner banner_mainStack;
    @BindView(R.id.banner_main_zoomStack)
    BGABanner banner_mainZoomStack;
    @BindView(R.id.banner_main_depth)
    BGABanner banner_mainDepth;

    private Engine mEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bgabanner_test);
        ButterKnife.bind(this);
        mEngine = TestApplication.getInstance().getEngine();
        setListener();
        loadData();
    }

    private void setListener() {
        banner_mainDefault.setDelegate(this);
        banner_mainCube.setDelegate(this);
    }

    private void loadData() {
        loadData(banner_mainDefault, 1);
        loadData(banner_mainCube, 2);
        loadData(banner_mainAccordion, 3);
        loadData(banner_mainFlip, 4);
        loadData(banner_mainRotate, 5);
        loadData(banner_mainAlpha, 6);
        loadData(banner_mainZoomFade, 3);
        loadData(banner_mainFade, 4);
        loadData(banner_mainZoomCenter, 5);
        loadData(banner_mainZoom, 6);
        loadData(banner_mainStack, 3);
        loadData(banner_mainZoomStack, 4);
        loadData(banner_mainDepth, 5);
    }

    private void loadData(final BGABanner banner, final int count) {
        mEngine.fetchItemsWithItemCount(count).enqueue(new Callback<BannerModel>() {
            @Override
            public void onResponse(Call<BannerModel> call, Response<BannerModel> response) {
                BannerModel bannerModel = response.body();

                /**
                 * 设置是否开启自动轮播，需要在 setData 方法之前调用，并且调了该方法后必须再调用一次 setData 方法
                 * 例如根据图片当图片数量大于 1 时开启自动轮播，等于 1 时不开启自动轮播
                 */
//                banner.setAutoPlayAble(bannerModel.imgs.size() > 1);

                banner.setAdapter(BGABannerTestActivity.this);
                banner.setData(bannerModel.imgs, bannerModel.tips);
            }

            @Override
            public void onFailure(Call<BannerModel> call, Throwable t) {
                Toast.makeText(TestApplication.getInstance(), "网络数据加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
        Glide.with(itemView.getContext())
                .load(model)
                .placeholder(R.drawable.holder)
                .error(R.drawable.holder)
                .dontAnimate()
                .centerCrop()
                .into(itemView);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
        Toast.makeText(banner.getContext(), "点击了第" + (position + 1) + "页", Toast.LENGTH_SHORT).show();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_main_visible:
                banner_mainDefault.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_main_invisible:
                banner_mainDefault.setVisibility(View.INVISIBLE);
                break;
            case R.id.tv_main_gone:
                banner_mainDefault.setVisibility(View.GONE);
                break;
            case R.id.tv_main_enable_auto_play:
                /**
                 * 设置是否开启自动轮播，需要在 setData 方法之前调用，并且调了该方法后必须再调用一次 setData 方法
                 * 例如根据图片当图片数量大于 1 时开启自动轮播，等于 1 时不开启自动轮播
                 */
                banner_mainDefault.setAutoPlayAble(true);
                break;
            case R.id.tv_main_disable_auto_play:
                /**
                 * 设置是否开启自动轮播，需要在 setData 方法之前调用，并且调了该方法后必须再调用一次 setData 方法
                 * 例如根据图片当图片数量大于 1 时开启自动轮播，等于 1 时不开启自动轮播
                 */
                banner_mainDefault.setAutoPlayAble(false);
                break;
            case R.id.tv_main_start_auto_play:
                // 仅在 autoPlayAble 为 true 时才会生效「开发者使用该库时不用调用该方法，这里只是为了演示而已，界面可见时在 BGABanner 内部已经帮开发者调用了该方方法」
                banner_mainDefault.startAutoPlay();
                break;
            case R.id.tv_main_stop_auto_play:
                // 仅在 autoPlayAble 为 true 时才会生效「开发者使用该库时不用调用该方法，这里只是为了演示而已，界面不可见时在 BGABanner 内部已经帮开发者调用了该方方法」
                banner_mainDefault.stopAutoPlay();
                break;
            case R.id.tv_main_select_page_one:
                banner_mainDefault.setCurrentItem(0);
                break;
            case R.id.tv_main_select_page_two:
                banner_mainDefault.setCurrentItem(1);
                break;
            case R.id.tv_main_select_page_three:
                banner_mainDefault.setCurrentItem(2);
                break;
            case R.id.tv_main_select_page_four:
                banner_mainDefault.setCurrentItem(3);
                break;
            case R.id.tv_main_select_page_five:
                banner_mainDefault.setCurrentItem(4);
                break;
            case R.id.tv_main_get_item_count:
                Toast.makeText(TestApplication.getInstance(), "广告条总页数为 " + banner_mainDefault.getItemCount(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_main_get_current_item:
                Toast.makeText(TestApplication.getInstance(), "广告当前索引位置为 " + banner_mainDefault.getCurrentItem(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_main_load_one_item:
                loadData(banner_mainDefault, 1);
                break;
            case R.id.tv_main_load_two_item:
                loadData(banner_mainDefault, 2);
                break;
            case R.id.tv_main_load_three_item:
                loadData(banner_mainDefault, 3);
                break;
            case R.id.tv_main_load_five_item:
                loadData(banner_mainDefault, 5);
                break;
            case R.id.tv_main_cube:
                banner_mainDefault.setTransitionEffect(TransitionEffect.Cube);
                break;
            case R.id.tv_main_depth:
                banner_mainDefault.setTransitionEffect(TransitionEffect.Depth);
                break;
            case R.id.tv_main_flip:
                banner_mainDefault.setTransitionEffect(TransitionEffect.Flip);
                break;
            case R.id.tv_main_rotate:
                banner_mainDefault.setTransitionEffect(TransitionEffect.Rotate);
                break;
            case R.id.tv_main_alpha:
                banner_mainDefault.setTransitionEffect(TransitionEffect.Alpha);
                break;
            case R.id.tv_main_listview_demo:
                startActivity(new Intent(this, ListViewDemoActivity.class));
                break;
            case R.id.tv_main_recyclerview_demo:
                startActivity(new Intent(this, RecyclerViewDemoActivity.class));
                break;
            case R.id.tv_main_fresco:
                startActivity(new Intent(this, FrescoDemoActivity.class));
                break;

            default:
                break;
        }
    }
}
