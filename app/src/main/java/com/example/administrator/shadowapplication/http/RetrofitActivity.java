package com.example.administrator.shadowapplication.http;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.example.administrator.shadowapplication.R;
import com.example.administrator.shadowapplication.crash_log.LogUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        GitHubService gitHubService = RetrofitUtil.getInstance().getAPIService(GitHubService.class);
        gitHubService.getData("fuxiaoyingzi").enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() != null) {
                    LogUtil.d("hh", "retrofit getData = " + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                LogUtil.d("hh", "retrofit getData error");
            }
        });

        gitHubService.getUserInfo("fuxiaoyingzi").enqueue(new Callback<GitModel>() {
            @Override
            public void onResponse(Call<GitModel> call, Response<GitModel> response) {
                if (response.isSuccessful()) {
                    LogUtil.d("hh", "retrofit getUserInfo: " + response.body().getName());
                }
            }

            @Override
            public void onFailure(Call<GitModel> call, Throwable t) {
                LogUtil.d("hh", "retrofit getUserInfo error");

            }
        });

        Observable<GitModel> observable = gitHubService.rxUserInfo("fuxiaoyingzi");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GitModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.d("hh", "retrofit rxUserInfo onError = " + e.getMessage());

                    }

                    @Override
                    public void onNext(GitModel gitModel) {
                        LogUtil.d("hh", "retrofit rxUserInfo = " + gitModel.getName());

                    }
                });


    }
}
