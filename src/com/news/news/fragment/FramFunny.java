package com.news.news.fragment;

import com.news.news.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FramFunny extends Fragment {
	private View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if(view==null){
			view=inflater.inflate(R.layout.fram_funny, null);
			Log.i("123", "FramFunny");
			
			//初始数据在这里写
		
			
		}else{
			((ViewGroup) view.getParent()).removeView(view); 
		}
		return view;
	}
}
