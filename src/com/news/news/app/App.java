package com.news.news.app;


import org.xutils.x;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.news.news.entity.User;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
public class App  extends Application{
	
	public static Context context;
    public static User user=new User();
    private static RequestQueue mQueue;
    
    
     
    
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		context =getApplicationContext();
		x.Ext.init(this);
		super.onCreate();
		
		/**
		 * Shark Z 
		 * 1.�� ��Ŀ ���е�ʱ�� ���� Volley ������� 
		 * 2.��ȡ RequestQueue ����
		 */
		this.mQueue=Volley.newRequestQueue(getApplicationContext()); 
		
		
	}
	
	
	/**
	 * ��ȡ������ ����
	 * @return mQueue
	 */
	public static RequestQueue getmQueue(){
		return mQueue;
	}
	
}
