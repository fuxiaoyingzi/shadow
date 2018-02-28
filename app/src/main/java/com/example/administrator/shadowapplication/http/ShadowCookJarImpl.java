package com.example.administrator.shadowapplication.http;

import java.net.CookieStore;
import java.net.HttpCookie;
import java.net.URI;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/2/27
 *     desc   :保存设置cook
 * </pre>
 */
public class ShadowCookJarImpl implements CookieJar {
    @Override
    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
    }

    @Override
    public List<Cookie> loadForRequest(HttpUrl url) {
        return null;
    }
}
