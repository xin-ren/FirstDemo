package com.example.ysd.firstdemo.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ysd.firstdemo.R;
import com.example.ysd.firstdemo.data.DataServer;
import com.example.ysd.firstdemo.entity.Status;

/**
 * Created by 任新 on 2016/12/29 15:27.
 * Function: HeaderAndFooterAdapter
 * Desc:
 */
public class HeaderAndFooterAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {
    public HeaderAndFooterAdapter() {
        super( R.layout.item_header_and_footer, DataServer.getSampleData(100));
    }

    public HeaderAndFooterAdapter(int dataSize) {
        super( R.layout.item_header_and_footer, DataServer.getSampleData(dataSize));
    }

    @Override
    protected void convert(BaseViewHolder helper, Status item) {
    }


}
