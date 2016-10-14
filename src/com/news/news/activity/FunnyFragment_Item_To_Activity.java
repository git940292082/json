package com.news.news.activity;


import com.news.news.R;
import com.news.news.entity.FunnyItem;

import android.os.Bundle;
import android.webkit.WebView;
import android.app.Activity;
import android.content.Intent;

public class FunnyFragment_Item_To_Activity extends Activity {

	private FunnyItem funny;

	private WebView webView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_funny_fragment__item__to_);


		//��ʼ���ؼ� 
		webView=(WebView) findViewById(R.id.webView_full_funny);

		//��ȡ intent funny
		Intent intent = getIntent();
		funny = (FunnyItem) intent.getSerializableExtra("funny");

		//Ϊ�ؼ����� ����

		//		webView ��ʾ ͼƬ 
		webView.loadUrl(funny.getUrl());



	}



}
