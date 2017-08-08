package com.haieros.kang_app.model;

/**
 * Created by Kang on 2017/8/7.
 */
public class WrapperTest {

    public static void main(String[] args) {

        Source source = new Source();
        Targetable targetable =  new Wrapper(source);
        targetable.method1();
        targetable.method2();
    }
}
