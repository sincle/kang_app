package com.haieros.kang_app.retroft;

import retrofit2.Retrofit;

/**
 * Created by Kang on 2017/8/7.
 */
public class RetrofitHelper {
    private static RetrofitHelper ourInstance = new RetrofitHelper();

    public static RetrofitHelper getInstance() {
        return ourInstance;
    }

    private RetrofitHelper() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("www.baidu.com/")
                .build();
    }
}
