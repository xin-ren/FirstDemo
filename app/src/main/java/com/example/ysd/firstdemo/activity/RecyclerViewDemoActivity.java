package com.example.ysd.firstdemo.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGABannerUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Create by 任新 on 2016/12/27 15:56
 * Function：RecycleView测试界面
 * Desc：
 */
public class RecyclerViewDemoActivity extends AppCompatActivity implements BGABanner.Adapter<ImageView, String>, BGABanner.Delegate<ImageView, String> {

    @BindView(R.id.rv_content)
    RecyclerView rv_content;

    private BGABanner mBanner;
    private ContentAdapter mContentAdapter;
    private Engine mEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_demo);
        ButterKnife.bind(this);
        mEngine = TestApplication.getInstance().getEngine();

        initRecyclerView();
        loadBannerData();
        loadContentData();
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        // 初始化适配器
        mContentAdapter = new ContentAdapter(rv_content);
        // 测试 item 点击事件
        mContentAdapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                // 注意：即使加了 HeaderView，这里返回的 position 也是从 0 开始的，在 BGARecyclerViewAdapter 的内部已经帮开发者减去了 HeaderView
                Toast.makeText(itemView.getContext(), "position = " + position + " " + mContentAdapter.getItem(position).title, Toast.LENGTH_SHORT).show();
            }
        });
        // 添加 HeaderView
        mContentAdapter.addHeaderView(getHeaderView());

        RecyclerView.LayoutManager layoutManager;

        // 测试 LinearLayoutManager 的情况
//        layoutManager = new LinearLayoutManager(this);

        // 测试 GridLayoutManager 的情况
        layoutManager = new GridLayoutManager(this, 2);

        rv_content.setLayoutManager(layoutManager);

        // 测试添加分割间隙
        rv_content.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                int position = parent.getChildAdapterPosition(view);
                // 注意：由于加了一个 HeaderView，所以是大于 0 时才加分隔间隙。onCanvas 就不演示了
                if (position > 0) {
                    int halfPadding = BGABannerUtil.dp2px(view.getContext(), 4);
                    outRect.set(halfPadding, halfPadding, halfPadding, halfPadding);
                }
            }
        });

        // 注意：这里调用了 getHeaderAndFooterAdapter 方法
        rv_content.setAdapter(mContentAdapter.getHeaderAndFooterAdapter());
    }

    /**
     * 初始化 HeaderView
     *
     * @return
     */
    private View getHeaderView() {
        View headerView = View.inflate(this, R.layout.layout_header, null);
        mBanner = (BGABanner) headerView.findViewById(R.id.banner);
        mBanner.setAdapter(this);
        mBanner.setDelegate(this);
        return headerView;
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
    public void onBannerItemClick(BGABanner banner, ImageView imageView, String model, int position) {
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

    private class ContentAdapter extends BGARecyclerViewAdapter<RefreshModel> {

        public ContentAdapter(RecyclerView recyclerView) {
            super(recyclerView, R.layout.item_normal);
        }

        @Override
        protected void fillData(BGAViewHolderHelper helper, int position, RefreshModel model) {
            helper.setText(R.id.tv_item_normal_title, model.title).setText(R.id.tv_item_normal_detail, model.detail);
        }
    }
}
