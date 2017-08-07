package com.haieros.kang_app.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.haieros.kang_app.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build();
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // method1();
        method2();
    }

    private void method2() {

    }

    public void method1() {
        Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Response> e) throws Exception {

                Log.e(TAG, "subscribe 发送 "+"---------------"+Thread.currentThread().getName());
                Request.Builder builder = new Request.Builder()
                        .url("http://api.avatardata.cn/MobilePlace/LookUp?key=ec47b85086be4dc8b5d941f5abd37a4e&mobileNumber=13021671512")
                        .get();
                Request request = builder.build();
                Call call = okHttpClient.newCall(request);
                Response response = call.execute();
                e.onNext(response);
            }
        }).map(new Function<Response, MobileAddress>() {
            @Override
            public MobileAddress apply(@NonNull Response response) throws Exception {

                Log.e(TAG, "map 线程:" + Thread.currentThread().getName() + "\n");

                if (response.isSuccessful()) {

                    ResponseBody body = response.body();
                    if (body != null) {
                        String result = body.string();
                        Log.e(TAG, "map:转换前:" + result);
                        MobileAddress mobileAddress = new Gson().fromJson(result, MobileAddress.class);

                        if(mobileAddress != null) {

                            Log.e(TAG, "mobileAddress:" + "-------"+mobileAddress.toString());
                        }
                        Log.e(TAG, "==================");
                        return mobileAddress;
                    }
                    return null;
                }
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<MobileAddress>() {
                    @Override
                    public void accept(@NonNull MobileAddress mobileAddress) throws Exception {
                        Log.e(TAG, "doOnNext 线程:" + Thread.currentThread().getName());
                        Log.e(TAG, "doOnNext : 保存成功" + mobileAddress.toString());
                    }
                })
                .subscribeOn(Schedulers.io())
                //.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MobileAddress>() {
                    @Override
                    public void accept(@NonNull MobileAddress mobileAddress) throws Exception {
                        Log.e(TAG, "subscribe 线程:" + Thread.currentThread().getName() + "\n");
                        Log.e(TAG, "成功:" + mobileAddress.toString() + "\n");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "subscribe 线程:" + Thread.currentThread().getName() + "\n");
                        Log.e(TAG, "失败：" + throwable.getMessage() + "\n");
                    }
                });
    }
}
