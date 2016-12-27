package com.example.ysd.firstdemo;

import com.example.ysd.firstdemo.entity.BannerModel;
import com.example.ysd.firstdemo.entity.RefreshModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Create by 任新 on 2016/12/27 15:25
 * Function：接口
 * Desc：
 */

public interface Engine {
    @GET("{itemCount}item.json")
    Call<BannerModel> fetchItemsWithItemCount(@Path("itemCount") int itemCount);

    @GET
    Call<List<RefreshModel>> loadContentData(@Url String url);
}
