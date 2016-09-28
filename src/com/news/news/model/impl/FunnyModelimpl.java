package com.news.news.model.impl;

import java.util.List;


import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.news.news.app.App;
import com.news.news.entity.Funny;
import com.news.news.entity.FunnyItem;
import com.news.news.model.CommonCallback;
import com.news.news.model.FunnyModel;
import com.news.news.untils.GlobalConsts;

/**
 * FunnyFragment model�� �ӿ� ʵ����
 * 
 * @author Shark Z
 * 
 */
public class FunnyModelimpl implements FunnyModel {

	private RequestQueue mQueue;

	/**
	 * ���췽��
	 */
	public FunnyModelimpl() {
		mQueue = App.getmQueue();

	}
	@Override
	public void getAllFunnys(final CommonCallback callback) {

		/**
		 * 1.���� ���� Funny �ķ��� 2.ʹ��ʲô ��ʽ ���� ��Ӧ�����أ����� Volley ���-->Ƶ�� С���� 3.��
		 * Application ���� ������� mQueue
		 */

		
//		String url="http://japi.juhe.cn/joke/img/text.from?page=1&pagesize=20&key=038af288ef6adda3c5c52193161e918d";
		
		
		// 1.StringRequest
		StringRequest stringRequest = new StringRequest(
				
				GlobalConsts.URL_FUNNY_FRAGMENT, new Listener<String>() {

					@Override
					public void onResponse(String response) {

//						Log.i("Funny", "response=" + response);
						// 1.ͨ�� ��־ -->���ݻ�ȡ�ɹ�

						// 2.������ȡ������Ӧ����
						Gson gson = new Gson();
						Funny resp = gson.fromJson(response, Funny.class);
						// ��ȡ ���е� Funny ���󼯺�
						List<FunnyItem> funnys = resp.getResult().getData();

//						Log.i("Funny", "funnys=" + funnys);// ͨ����־ -->�����ѻ��

						// 3.���ûص����� ������ ����
						callback.onDataLoaded(funnys);

					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
//						Log.v("VolleyError error", "error=" + error);
					}
				});

		// 2.������������
		mQueue.add(stringRequest);
	}

}
