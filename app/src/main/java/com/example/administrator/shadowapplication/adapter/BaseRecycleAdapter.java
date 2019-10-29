package com.example.administrator.shadowapplication.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.administrator.shadowapplication.R;

import java.util.List;

import rx.Observable;
import rx.Subscriber;


/**
 * @author 付影影
 * @desc
 * @date 2019/10/23
 */
public class BaseRecycleAdapter extends BaseQuickAdapter<Shadow, BaseViewHolder> {
    public BaseRecycleAdapter(@Nullable List<Shadow> data) {
        super(R.layout.item_base_recycle_adapter, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Shadow item) {
        helper.setText(R.id.name, item.getName())
                .setText(R.id.age, item.getAge())
                .addOnClickListener(R.id.constraintLayout);

        Observable.create(new Observable.OnSubscribe<String>() {

            @Override
            public void call(Subscriber<? super String> subscriber) {

            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        });

    }
}
