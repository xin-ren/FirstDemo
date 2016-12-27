package com.example.ysd.firstdemo.event;


/**
 * Create by 任新 on 2016/12/27 13:37
 * Function：第二个Event
 * Desc：
 */
public class SecondEvent {
    private String mMsg;

    public SecondEvent(String msg) {
        // TODO Auto-generated constructor stub
        mMsg = "MainEvent:" + msg;
    }

    public String getMsg(){
        return mMsg;
    }
}
