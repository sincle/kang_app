package com.haieros.kang_app.model;

/**
 * Created by Kang on 2017/8/7.
 */
public class Adapter extends Source implements Targetable{

    @Override
    public void method2() {
        System.out.println("this is targetable method2");
    }
}
