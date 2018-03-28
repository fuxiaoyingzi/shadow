package com.example.administrator.shadowapplication.annotation;

/**
 * Author : shadow
 * Desc :应用注解，控制参数的传递
 * Date :2018/3/27/027
 */
public class TestCustomAnnomation {
    public TestCustomAnnomation() {
        setCustomAnno(CustomAnnomation.IMAGE_TYPE_CIRCLE);
    }

    private void setCustomAnno(@CustomAnnomation int imageType) {
        switch (imageType) {
            case CustomAnnomation.IMAGE_TYPE_CIRCLE:
                break;
            case CustomAnnomation.IMAGE_TYPE_NORMAL:
                break;
            case CustomAnnomation.IMAGE_TYPE_ROUND:
                break;
            default:
        }
    }

}
