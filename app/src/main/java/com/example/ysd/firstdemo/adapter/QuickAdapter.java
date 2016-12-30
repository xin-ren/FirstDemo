package com.example.ysd.firstdemo.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ysd.firstdemo.R;
import com.example.ysd.firstdemo.data.DataServer;
import com.example.ysd.firstdemo.entity.Status;

/**
 * Created by 任新 on 2016/12/30 13:30.
 * Function:QuickAdapter
 * Desc:
 */
public class QuickAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {
    public QuickAdapter() {
        super(R.layout.tweet, DataServer.getSampleData(100));
    }

    public QuickAdapter(int dataSize) {
        super(R.layout.layout_animation, DataServer.getSampleData(dataSize));
    }

    @Override
    protected void convert(BaseViewHolder helper, Status item) {
        switch (helper.getLayoutPosition() %
                3) {
            case 0:
                helper.setImageResource(R.id.img, R.drawable.animation_img1);
                break;
            case 1:
                helper.setImageResource(R.id.img, R.drawable.animation_img2);
                break;
            case 2:
                helper.setImageResource(R.id.img, R.drawable.animation_img3);
                break;
        }
        helper.setText(R.id.tweetName, "Hoteis in Rio de Janeiro");
        helper.setText(R.id.tweetText, "O ever youthful,O ever weeping");

    }

}
