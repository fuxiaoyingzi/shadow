package com.example.administrator.shadowapplication.design_pattern.builder;

import android.util.Log;

import com.example.administrator.shadowapplication.design_pattern.builder.BuilderDemo;

/**
 * Created by Administrator on 2017/6/22.
 *
 * 测试构建模式builder ，链式编程
 */

public class TestBuilderDemo {

    public TestBuilderDemo()  {
        try {
            BuilderDemo demo = new BuilderDemo.UserBuilder("付彩华","付小影子")
                    .address("河南")
                    .age(18)
                    .builder();
            Log.d("hh","test builder pattern :"+demo.getFirstName());
        } catch (Exception e) {
            Log.d("hh","年龄超过125");
            e.printStackTrace();
        }


    }
}
