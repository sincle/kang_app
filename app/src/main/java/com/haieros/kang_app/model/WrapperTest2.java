package com.haieros.kang_app.model;

/**
 * Created by Kang on 2017/8/7.
 */
public class WrapperTest2 {

    public static void main(String[] args) {

        Sourceable source1 = new SourceSub1();
        Sourceable source2 = new SourceSub2();

        source1.method1();
        source1.method2();

        source2.method1();
        source2.method2();
    }
}
