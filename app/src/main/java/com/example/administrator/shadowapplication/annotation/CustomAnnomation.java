package com.example.administrator.shadowapplication.annotation;

import androidx.annotation.IntDef;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.example.administrator.shadowapplication.annotation.CustomAnnomation.IMAGE_TYPE_CIRCLE;
import static com.example.administrator.shadowapplication.annotation.CustomAnnomation.IMAGE_TYPE_NORMAL;
import static com.example.administrator.shadowapplication.annotation.CustomAnnomation.IMAGE_TYPE_ROUND;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;

/**
 * Author : shadow
 * Desc :
 * Date :2018/3/27/027
 */
@Documented
@Retention(RetentionPolicy.CLASS)//注解类型，编译时注解
//注解范围
@Target(value = {
        FIELD,//修饰成员变量
        LOCAL_VARIABLE,//修饰局部变量
        METHOD,//修饰方法
        PARAMETER,//修饰参数
        TYPE})//修饰类 接口，枚举类型
@IntDef({IMAGE_TYPE_NORMAL, IMAGE_TYPE_CIRCLE, IMAGE_TYPE_ROUND})
public @interface CustomAnnomation {
    public static final int IMAGE_TYPE_NORMAL = 0; //默认显示
    public static final int IMAGE_TYPE_CIRCLE = 1; //圆形显示
    public static final int IMAGE_TYPE_ROUND = 2; //圆角显示

}


