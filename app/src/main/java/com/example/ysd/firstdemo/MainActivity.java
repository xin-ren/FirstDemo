package com.example.ysd.firstdemo;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ysd.firstdemo.activity.FlycoTabLayoutTestActivity;
import com.example.ysd.firstdemo.activity.RecyclerViewTestActivity;
import com.example.ysd.firstdemo.event.FirstEvent;
import com.example.ysd.firstdemo.event.SecondEvent;
import com.example.ysd.firstdemo.event.ThirdEvent;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Create by 任新 on 2016/12/23 10:55
 * Function：主页面
 * Desc：
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_okHttp_mainActivity)
    Button btn_okHttp;
    @BindView(R.id.iv_image_mainActivity)
    ImageView iv_image;
    @BindView(R.id.btn_retrofit_mainActivity)
    Button btn_retrofit;
    @BindView(R.id.btn_gson_mainActivity)
    Button btn_gson_mainActivity;
    @BindView(R.id.btn_leakcanary_mainActivity)
    Button btn_leakcanary_mainActivity;
    @BindView(R.id.btn_eventBus_mainActivity)
    Button btn_eventBus_mainActivity;
    @BindView(R.id.btn_BGABanner_mainActivity)
    Button btn_BGABanner_mainActivity;
    @BindView(R.id.btn_FlycoTabLayout_mainActivity)
    Button btn_flycoTabLayout_mainActivity;
    @BindView(R.id.btn_RecyclerView_mainActivity)
    Button btn_recyclerView_mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        //简单的glide图片加载示例
        Glide.with(this)
                .load("https://www.baidu.com/img/bd_logo1.png")
                .skipMemoryCache(true) //设置跳过内存缓存
                .into(iv_image);
        //1,设置加载中以及加载失败图片
        //Glide.with(this).load("https://www.baidu.com/img/bd_logo1.png").placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(iv_image);
        //2,设置下载优先级
        //Glide.with(this).load(imageUrl).priority(Priority.NORMAL).into(imageView);
        //3,设置缓存策略
        //Glide.with(this).load("https://www.baidu.com/img/bd_logo1.png").diskCacheStrategy(DiskCacheStrategy.ALL).into(iv_image);
        //4,设置加载动画
        //Glide.with(this).load().animate().into();
        //5,设置缩略图支持
        //Glide.with(this).load().thumbnail().into();
        //6,设置加载尺寸
        //Glide.with(this).load().override().into();
        //7,设置动态转换
        //Glide.with(this).load().centerCrop().into();
        //8,项目中有很多需要先下载图片然后再做一些合成的功能，比如项目中出现的图文混排，该如何实现
        /*Glide.with(this).load(imageUrl).centerCrop().into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                imageView.setImageDrawable(resource);
            }
        });*/
        //9,设置监听请求接口
       /* Glide.with(this).load(imageUrl).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                //imageView.setImageDrawable(resource);
                return false;
            }
        }).into(imageView);*/
        //10,设置动态GIF加载方式
        //Glide.with(this).load(imageUrl).asBitmap().into(imageView);//显示gif静态图片
        //Glide.with(this).load(imageUrl).asGif().into(imageView);//显示gif动态图片
        //11,缓存的动态清理
        //Glide.get(this).clearDiskCache();//清理磁盘缓存 需要在子线程中执行
        //Glide.get(this).clearMemory();//清理内存缓存  可以在UI主线程中进行
    }

    @OnClick({R.id.btn_okHttp_mainActivity, R.id.btn_retrofit_mainActivity, R.id.btn_gson_mainActivity,
            R.id.btn_leakcanary_mainActivity, R.id.btn_eventBus_mainActivity, R.id.btn_FlycoTabLayout_mainActivity, R.id.btn_RecyclerView_mainActivity})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_okHttp_mainActivity:
                startActivity(new Intent(MainActivity.this, OkHttpTestActivity.class));
                break;
            case R.id.btn_retrofit_mainActivity:
                startActivity(new Intent(MainActivity.this, RetrofitTestActivity.class));
                break;
            case R.id.btn_gson_mainActivity:
                final String url = "https://api.github.com/gists/c2a7c39532239ff261be";
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        parseJsonForGson(url);
                    }
                }).start();
                break;
            case R.id.btn_leakcanary_mainActivity:
                startAsyncTask();
                break;
            case R.id.btn_eventBus_mainActivity:
                startActivity(new Intent(MainActivity.this, EventBusTestActivity.class));
                break;
            case R.id.btn_FlycoTabLayout_mainActivity:
                startActivity(new Intent(MainActivity.this, FlycoTabLayoutTestActivity.class));
                break;
            case R.id.btn_RecyclerView_mainActivity:
                startActivity(new Intent(MainActivity.this, RecyclerViewTestActivity.class));
                break;
        }
    }

    /**
     * 此异步任务是一个匿名类，因此具有隐藏对外部引用
     * MainActivity 的类。如果活动获取摧毁之前任务完成 （例如旋转）
     * 活动实例会泄漏
     */
    void startAsyncTask() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                //做一些工作进展缓慢的背景
                SystemClock.sleep(20000);
                return null;
            }
        }.execute();
    }

    /**
     * 使用GSON解析JSON
     *
     * @param url
     */
    public void parseJsonForGson(String url) {
        Gson gson = new Gson();
        OkHttpClient client = new OkHttpClient();
//        url = "https://api.github.com/gists/c2a7c39532239ff261be"
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            Gist gist = gson.fromJson(response.body().charStream(), Gist.class);
            for (Map.Entry<String, GistFile> entry : gist.files.entrySet()) {
                System.out.println(entry.getKey());
                System.out.println(entry.getValue().content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Gist {
        Map<String, GistFile> files;
    }

    static class GistFile {
        String content;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        String msg = "onEventMainThread收到了消息：" + event.getMsg();
        Log.d("MainActivity", msg);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    //SecondEvent接收函数一
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(SecondEvent event) {
        String msg = "onEventMainThread收到了消息：" + event.getMsg();
        Log.d("MainActivity", "onEventMainThread收到了消息：" + event.getMsg());
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    //SecondEvent接收函数二
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onEventBackgroundThread(SecondEvent event) {
        Log.d("MainActivity", "onEventBackground收到了消息：" + event.getMsg());
    }

    //SecondEvent接收函数三
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEventAsync(SecondEvent event) {
        Log.d("MainActivity", "onEventAsync收到了消息：" + event.getMsg());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ThirdEvent event) {
        String msg = "onEventMainThread收到了消息：" + event.getMsg();
        Log.d("MainActivity", "OnEvent收到了消息：" + event.getMsg());
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
