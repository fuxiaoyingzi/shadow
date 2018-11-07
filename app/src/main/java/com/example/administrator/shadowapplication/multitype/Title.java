package com.example.administrator.shadowapplication.multitype;

/**
 * Author : shadow
 * Desc :
 * Date :2018/6/1/001
 */

public class Title {
    public Title(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String title;
    public String url;
}
