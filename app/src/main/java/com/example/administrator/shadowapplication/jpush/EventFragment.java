package com.example.administrator.shadowapplication.jpush;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.administrator.shadowapplication.R;


public class EventFragment extends BaseFragment implements View.OnClickListener{
	private static final String TAG="EventFragment";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.events_fragment, container, false);
		initView(v);
		return v;
	}

	private void initView(View v) {
		v.findViewById(R.id.button_login).setOnClickListener(this);
		v.findViewById(R.id.button_register).setOnClickListener(this);
		v.findViewById(R.id.button_purchase).setOnClickListener(this);
		v.findViewById(R.id.button_browse).setOnClickListener(this);
		v.findViewById(R.id.button_calculate).setOnClickListener(this);
		v.findViewById(R.id.button_count).setOnClickListener(this);
	}
	@Override
	public void onClick(View v){
		switch (v.getId()) {
			case R.id.button_login:
				JEventUtils.onLoginEvent(getActivity());
				Toast.makeText(getActivity().getApplicationContext(), "button_login", Toast.LENGTH_LONG).show();
				break;
			case R.id.button_register:
				JEventUtils.onRegisterEvent(getActivity());
				Toast.makeText(getActivity().getApplicationContext(), "button_register",Toast.LENGTH_SHORT).show();
				break;
			case R.id.button_purchase:
				// 应用市场中游戏的下载情况：游戏类型，下载次数，价格
				JEventUtils.onCalculateEvent2(getActivity());
				Toast.makeText(getActivity().getApplicationContext(), "button_purchase",Toast.LENGTH_SHORT).show();
				break;
			case R.id.button_calculate:
				JEventUtils.onCalculateEvent(getActivity());
				Toast.makeText(getActivity().getApplicationContext(), "button_calculate",Toast.LENGTH_SHORT).show();
				break;
			case R.id.button_count:
				JEventUtils.onCountEvent(getActivity());
				Toast.makeText(getActivity().getApplicationContext(), "button_count",Toast.LENGTH_SHORT).show();
				break;
			case R.id.button_browse:
				JEventUtils.onBrowseEvent(getActivity());
				Toast.makeText(getActivity().getApplicationContext(), "button_browse",Toast.LENGTH_SHORT).show();
				break;
		}
	}
}