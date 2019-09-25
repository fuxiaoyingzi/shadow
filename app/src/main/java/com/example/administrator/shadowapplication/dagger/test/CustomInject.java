package com.example.administrator.shadowapplication.dagger.test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * @author 付影影
 * @desc
 * @date 2019/9/25
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomInject {
}
