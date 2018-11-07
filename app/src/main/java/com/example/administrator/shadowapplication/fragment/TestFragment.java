package com.example.administrator.shadowapplication.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.administrator.shadowapplication.R;
import com.example.administrator.shadowapplication.activity.MaterialDesignActivity;

/**
 * Created by Administrator on 2017/6/6.
 */

public class TestFragment extends Fragment {
    private TextView textView;
    private static TestFragment fragment;
    private FragmentCallBack callBack;


    public static TestFragment newInstance() {
        Bundle bundle = new Bundle();
        bundle.putString("name", "shadow");
        fragment = new TestFragment();
        fragment.setArguments(bundle);
        return fragment;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callBack = (FragmentCallBack) getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        textView = (TextView) view.findViewById(R.id.tv_fragment);
        textView.setAnimation(AnimationUtils.loadAnimation(getActivity(),R.anim.anim_in));
        textView.getAnimation().start();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(textView,"alpha",100);
        objectAnimator.setDuration(10000);
        objectAnimator.start();
        textView.animate().alphaBy(0.2f).setDuration(200).alpha(1).start();

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(objectAnimator).with(objectAnimator).after(objectAnimator);
        animatorSet.playTogether(animatorSet);

        PropertyValuesHolder mPropertyValueHolder1 = PropertyValuesHolder.ofFloat("scaleX",1.0f,1.5f);
        PropertyValuesHolder mPropertyValueHolder2 = PropertyValuesHolder.ofFloat("alpha",0.1f,1.0f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(textView,mPropertyValueHolder1,mPropertyValueHolder2);
        animator.setDuration(2000);
        animator.start();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.callBack("hello shadow ，我是testFragment的回调");
                Intent intent = new Intent(getActivity(), MaterialDesignActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.anim_in, R.anim.anim_out);

            }
        });
        return view;
    }

    public void updateFragmentContent(String content) {
        textView.setText(content);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("hh", getArguments().getString("name"));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
