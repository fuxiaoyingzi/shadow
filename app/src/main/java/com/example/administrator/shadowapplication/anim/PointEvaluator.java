package com.example.administrator.shadowapplication.anim;

import android.animation.TypeEvaluator;

/**
 * Author : shadow
 * Desc : 实体类 自定义计算规则
 * Date :2019/1/14/014
 */
public class PointEvaluator implements TypeEvaluator<Point> {
    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        int res = (int) (startValue.getX() + fraction * (endValue.getX() - startValue.getX()));
        Point point = new Point(res);
        return point;
    }
}
