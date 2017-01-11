package com.example.ysd.firstdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Create by 任新 on 2016/12/23 13:50
 * Function：okHttp网络请求测试界面
 * Desc：
 */
public class OkHttpTestActivity extends AppCompatActivity {

    private String TAG = "OkHttpTestActivity";
    public static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    @BindView(R.id.btn_get_okHttpTestActivity)
    Button btn_get_okHttpTestActivity;
    @BindView(R.id.btn_post_okHttpTestActivity)
    Button btn_post_okHttpTestActivity;
    @BindView(R.id.btn_fileCommit_okHttpTestActivity)
    Button btn_fileCommit_okHttpTestActivity;
    @BindView(R.id.btn_fileDownLoad_okHttpTestActivity)
    Button btn_fileDownLoad_okHttpTestActivity;
//    @BindView(R.id.iv_toBack_okHttpTestActivity)
//    ImageView iv_toBackOkHttpTestActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_test);

        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_get_okHttpTestActivity, R.id.btn_post_okHttpTestActivity, R.id.btn_fileCommit_okHttpTestActivity, R.id.btn_fileDownLoad_okHttpTestActivity})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_okHttpTestActivity:
                okHttpGet();
//                okHttpGet2();
                break;
            case R.id.btn_post_okHttpTestActivity:
                String url = "https://confluence.utsoft.cc/pages/viewpage.action?pageId=8964499";
                String json = "";
                if (url != null) {
                    okHttpPostJson(url, json);
//                    okHttpPostParams(url);
                } else {
                    Toast.makeText(OkHttpTestActivity.this, "Url为空，发送不了请求", Toast.LENGTH_SHORT);
                }
                break;
            case R.id.btn_fileCommit_okHttpTestActivity:
                final String url1 = "https://confluence.utsoft.cc/pages/viewpage.action?pageId=8964499";
                if ("".equals(url1) || url1 == null) {
                    Toast.makeText(OkHttpTestActivity.this, "Url为空，发送不了请求", Toast.LENGTH_SHORT);
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            okHttpPostFile(url1);
                        }
                    }).start();
//                    okHttpCommitImg(url1);
//                    okHttpCommitFile(url1);
                }
                break;
            case R.id.btn_fileDownLoad_okHttpTestActivity:
                String url2 = "https://confluence.utsoft.cc/pages/viewpage.action?pageId=8964499";
                if ("".equals(url2) || url2 == null) {
                    Toast.makeText(OkHttpTestActivity.this, "Url为空，发送不了请求", Toast.LENGTH_SHORT);
                } else {
                    okHttpLoadImg(url2);
//                    okHttpLoadFile(url2);
//                    okHttpLoadFile2(url2);
                }
                break;
//            case R.id.iv_toBack_okHttpTestActivity:
//                onBackPressed();
//                break;
        }
    }

    //okHttp的GET请求方式
    private void okHttpGet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //创建okHttpClient对象
                OkHttpClient mOkHttpClient = new OkHttpClient();
                //创建一个request
                Request request = new Request.Builder().url("https://confluence.utsoft.cc/pages/viewpage.action?pageId=8964499").build();
                //new call
                Call call = mOkHttpClient.newCall(request);
                try {
                    Response response = call.execute();
                    if (response.isSuccessful()) {
//                        Toast.makeText(OkHttpTestActivity.this,"请求成功",Toast.LENGTH_SHORT).show();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(OkHttpTestActivity.this, "请求成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                //请求加入调度
//                call.enqueue(new Callback() {
//                    @Override
//                    public void onFailure(Call call, IOException e) {
//
//                    }
//
//                    @Override
//                    public void onResponse(Call call, Response response) throws IOException {
//                            String htmlStr = response.body().string();
//                    }
//                });

            }
        }).start();
    }

    //okHttp的GET请求方式
    private void okHttpGet2() {
        try {
            OkHttpClient client = new OkHttpClient.Builder().build();
            Request request = new Request.Builder().url("https://confluence.utsoft.cc/pages/viewpage.action?pageId=8964499").build();

            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String r = response.body().string();
                    if (response.isSuccessful()) {
                        Log.i(TAG, "httpGet1 OK: " + r);
                    } else {
                        Log.i(TAG, "httpGet1 error: " + r);
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * POST提交Json数据
     *
     * @param url
     * @param json
     */
    private void okHttpPostJson(String url, String json) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String r = response.body().string();
                if (response.isSuccessful()) {
                    Log.i(TAG, "httpPost1 OK: " + r);
                } else {
                    Log.i(TAG, "httpPost1 error: " + r);
                }
            }
        });
//
//        Response response = null;
//        try {
//            response = client.newCall(request).execute();
//            if (response.isSuccessful()) {
//                 response.body().string();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    /**
     * POST上传键值对
     *
     * @param url
     */
    private void okHttpPostParams(String url) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder().add("price", "25").add("历史", "张三").build();
        Request request = new Request.Builder().url(url).post(body).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String r = response.body().string();
                if (response.isSuccessful()) {
                    Log.i(TAG, "httpGet1 OK: " + r);
                } else {
                    Log.i(TAG, "httpGet1 error: " + r);
                }
            }
        });
    }


    /**
     * post提交文件
     *
     * @param url
     */
    private void okHttpPostFile(String url) {
        OkHttpClient client = new OkHttpClient();
        String path = Environment.getExternalStorageDirectory().getPath() + "/test.txt/";
        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
//        System.out.println(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * okHttp上传图片
     *
     * @param url
     */
    private void okHttpCommitImg(String url) {
        OkHttpClient client = new OkHttpClient();
        // mImgUrls为存放图片的url集合
        List<String> mImgUrls = new ArrayList();
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (int i = 0; i < mImgUrls.size(); i++) {
            File f = new File(mImgUrls.get(i));
            if (f != null) {
                builder.addFormDataPart("img", f.getName(), RequestBody.create(MEDIA_TYPE_PNG, f));
            }
        }
        //添加其它信息
//        builder.addFormDataPart("time",takePicTime);
//        builder.addFormDataPart("mapX", SharedInfoUtils.getLongitude());
//        builder.addFormDataPart("mapY",SharedInfoUtils.getLatitude());
//        builder.addFormDataPart("name",SharedInfoUtils.getUserName());
        MultipartBody requestBody = builder.build();
        //构建请求
        Request request = new Request.Builder()
                .url(url)//地址
                .post(requestBody)//添加请求体
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
//                Toast.makeText(OkHttpTestActivity.this, "上传失败", Toast.LENGTH_LONG).show();
                System.out.println("上传失败:" + e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                Toast.makeText(OkHttpTestActivity.this, "上传成功", Toast.LENGTH_LONG).show();
                System.out.println("上传照片成功：response = " + response.body().string());
            }
        });
    }

    /**
     * okHttp上传文件
     *
     * @param url
     */
    private void okHttpCommitFile(String url) {
        File file = new File("fileDir", "test.txt");
        if (!file.exists()) {
            file.mkdirs();
        }
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", "test.txt", fileBody)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        final okhttp3.OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = httpBuilder
                //设置超时
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "uploadMultiFile() e=" + e);
            }


            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "uploadMultiFile() response=" + response.body().string());
            }
        });
    }


    /**
     * okHttp下载图片
     *
     * @param url
     */
    private void okHttpLoadImg(String url) {
        final OkHttpClient client = new OkHttpClient();
        rx.Observable.just(url)
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String s) {
                        final okhttp3.Request request = new okhttp3.Request.Builder()
                                .url(s)
                                .build();
                        try {
                            final Response response = client.newCall(request).execute();
                            //从InputStream中得到bitmap
                            return BitmapFactory.decodeStream(response.body().byteStream());
                        } catch (IOException e) {
                            e.printStackTrace();
                            throw new Error(e);
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Bitmap>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "error:" + e.toString());
                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
//                        mImageView.setImageBitmap(bitmap);
                    }
                });
    }

    /**
     * okHttp下载文件
     *
     * @param url
     */
    private void okHttpLoadFile(String url) {
        final OkHttpClient client = new OkHttpClient();
        final File fileDir = this.getDir("", MODE_PRIVATE);
        File dirFile = new File(fileDir, "");
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        rx.Observable.just(url)
                .map(new Func1<String, File>() {
                    @Override
                    public File call(final String s) {
                        final okhttp3.Request request = new okhttp3.Request.Builder()
                                .url(s)
                                .build();
                        //
                        InputStream is = null;
                        FileOutputStream fos = null;
                        byte[] buffer = new byte[1024];
                        int lenght = 0;
                        try {
                            final Response response = client.newCall(request).execute();
                            is = response.body().byteStream();
                            File tempFile = new File(fileDir, "");
                            fos = new FileOutputStream(tempFile);
                            //
                            while ((lenght = is.read(buffer)) > 0) {
                                fos.write(buffer, 0, lenght);
                            }
                            fos.flush();
                            //
                            return tempFile;
                        } catch (IOException e) {
                            e.printStackTrace();
                            throw new Error(e);
                        } finally {
                            try {
                                if (is != null) is.close();
                            } catch (IOException e) {
                            }
                            try {
                                if (fos != null) fos.close();
                            } catch (IOException e) {
                            }
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<File>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "error:" + e.toString());
                    }

                    @Override
                    public void onNext(File file) {
                        Log.i(TAG, file == null ? "file is null" : file.getAbsolutePath());
                    }
                });
    }

    /**
     * okHttp下载文件2
     *
     * @param url
     */
    private void okHttpLoadFile2(String url) {
        Request request = new Request.Builder().url(url).build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                String SDPath = Environment.getExternalStorageDirectory().getAbsolutePath();
                try {
                    is = response.body().byteStream();
//                    long total = response.body().contentLength();
                    File file = new File(SDPath, "test.log");
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    fos.flush();
                    Log.d(TAG, "文件下载成功");
                } catch (Exception e) {
                    Log.d(TAG, "文件下载失败");
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
    }
}
