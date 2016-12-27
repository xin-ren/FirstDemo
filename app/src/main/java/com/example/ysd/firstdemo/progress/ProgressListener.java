package com.example.ysd.firstdemo.progress;

/**
 * Create by 任新 on 2016/12/26 14:50
 * Function：接口（监听下载的进度）
 * Desc：
 */
public interface ProgressListener {
    /**
     * @param progress     已经下载或上传字节数
     * @param total        总字节数
     * @param done         是否完成
     */
    void onProgress(long progress, long total, boolean done);
}
