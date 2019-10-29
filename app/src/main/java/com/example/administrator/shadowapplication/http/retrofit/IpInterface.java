package com.example.administrator.shadowapplication.http.retrofit;

import com.example.administrator.shadowapplication.dagger.test.User;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * @author 付影影
 * @desc
 * @date 2019/10/18
 */
public interface IpInterface {
    @GET("getIpInfo.php?ip = 59.108.54.37")
    Call<IpModel> getIpMsg();

    /**
     * 动态请求地址路径
     * @param path
     * @return
     */
    @GET("{path}/getIpInfo.php?ip = 59.108.54.37")
    Call<IpModel> getIpMsg2(@Path("path") String path);


    /**
     * 单个请求参数
     * @param ip
     * @return
     */
    @GET("getIpInfo.php")
    Call<IpModel> getIpMsg3(@Query("ip") String ip);


    /**
     * 多个请求参数
     * @param params
     * @return
     */
    @GET("getIpInfo.php")
    Call<IpModel> getIpMsg4(@QueryMap Map<String, String> params);


    /**
     * 静态 添加单个头部
     * @return
     */
    @GET("getIpInfo.php")
    @Headers("Accept-Encoding:application/json")
    Call<IpModel> addHeader();

    /**
     * 静态添加 多个头部
     * @return
     */
    @GET("getIpInfo.php")
    @Headers({"Accept-Encoding:application/json", "User-Agent:MoonRetrofit"})
    Call<IpModel> addHeader2();


    /**
     * 动态添加头部请求信息
     * @param location
     * @return
     */
    @GET("getIpInfo.php")
    Call<IpModel> addHeader3(@Header("Location") String location);


    /**
     * 单个键值对 post请求参数
     * @param ip
     * @return
     */
    @FormUrlEncoded
    @POST("getIpInfo.php")
    Call<IpModel> postIpMsg(@Field("ip") String ip);

    /**
     * 多个键值对 请求参数
     *  @FormUrlEncoded 表示这是个表单请求
     */
    @FormUrlEncoded
    @POST("getIpInfo.php")
    Call<IpModel> postIpMsg2(@FieldMap Map<String,String> params);

    /**
     * json 字符串形式 请求参数，retrofit会自动把user对象 转换为json字符串
     * @param user
     * @return
     */
    @POST("getIpInfo.php")
    Call<IpModel> postIpMsg3(@Body User user);


    /**
     * 单个文件上传
     *  @Multipart 表示 允许多个@part
     */
    @Multipart
    @POST("getIpInfo.php")
    Call<IpModel> postIpMsg4(@Part MultipartBody.Part photo, @Part("description")RequestBody desc);


    /**
     * 多个文件上传
     * @param photo
     * @param desc
     * @return
     */
    @Multipart
    @POST("getIpInfo.php")
    Call<IpModel> postIpMsg5(@PartMap Map<String,RequestBody> photo, @Part("description")RequestBody desc);


}
