package com.example.administrator.shadowapplication.http.retrofit;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.administrator.shadowapplication.R;
import com.example.administrator.shadowapplication.crash_log.LogUtil;
import com.example.administrator.shadowapplication.http.GitModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
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


    public void testRxJava() {
        //1. 创建观察者对象
        Subscriber<String> mSubscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError");
            }

            @Override
            public void onNext(String s) {
                System.out.println("hello -" + s);
            }

            @Override
            public void onStart() {
                super.onStart();
            }
        };

        //2. 创建被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello");
                subscriber.onNext("shadow");
                subscriber.onCompleted();
            }
        });

        //3. 订阅
        observable.subscribe(mSubscriber);
    }

    public void testObservable() {
        //toList操作符将发射多项数据且为每一项数据调用onNext方法的Observable发射的多项数据组合成一个 List，
        // 然后调用一次onNext方法传递整个列表
        Observable.just(1, 2, 3).toList()
                .subscribe(new Action1<List<Integer>>() {
                    @Override
                    public void call(List<Integer> integers) {
                        for (Integer integers1 : integers) {
                            //输出 call -- 1，call -- 2，call -- 3
                            Log.d("hh", "call -- " + integers1);
                        }
                    }
                });


        //toSortedList操作符类似于toList操作符；不同的是，它会对产生的列表排序，默认是自然升序。
        // 如果发 射的数据项没有实现Comparable接口，会抛出一个异常。
        // 当然，若发射的数据项没有实现Comparable接口， 可以使用toSortedList（Func2）变体，
        // 其传递的函数参数Func2会作用于比较两个数据项。
        Observable.just(1, 4, 5, 2, 6, 3).toSortedList()
                .subscribe(new Action1<List<Integer>>() {
                    @Override
                    public void call(List<Integer> integers) {
                        for (Integer integers1 : integers) {
                            //输出 call -- 1，call -- 2，call -- 3,call -- 4，call -- 5，call -- 6
                            Log.d("hh", "call -- " + integers1);
                        }
                    }
                });


        //toMap操作符收集原始Observable发射的所有数据项到一个Map（默认是HashMap），然后发射这个 Map。
        // 你可以提供一个用于生成Map的key的函数，也可以提供一个函数转换数据项到Map存储的值（默认 数据项本身就是值）
    }
}
