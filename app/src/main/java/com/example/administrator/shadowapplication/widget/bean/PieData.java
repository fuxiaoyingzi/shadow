package com.example.administrator.shadowapplication.widget.bean;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/1/24
 *     desc   :
 * </pre>
 */
public class PieData {
    private String name;
    private float percentage;
    private float angle;
    private float value;
    private int color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
