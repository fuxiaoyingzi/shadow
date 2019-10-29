package com.example.administrator.shadowapplication.http.retrofit;


import com.example.administrator.shadowapplication.http.GitModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


/**
 * <pre>
 *     author : Administrator
 *     time   : 2018/3/8
 *     desc   :
 * </pre>
 */


public interface GitHubService {
   String BASE_URL = "https://api.github.com";

   @GET("users/{user}")
   Call<String> getData(@Path("user")String user);

   @GET("users/{user}")
    Call<GitModel> getUserInfo(@Path("user")String user);

   @GET("users/{user}")
   Observable<GitModel> rxUserInfo(@Path("user")String user);
}
