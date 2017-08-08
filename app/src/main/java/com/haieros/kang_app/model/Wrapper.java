package com.haieros.kang_app.model;

/**
 * Created by Kang on 2017/8/7.
 */
public class Wrapper implements Targetable{

    private Source source;

    public Wrapper(Source source){

        this.source = source;
    }

    @Override
    public void method1() {
        source.method1();
    }

    @Override
    public void method2() {

        System.out.println("this is targetable method2");
    }
}
