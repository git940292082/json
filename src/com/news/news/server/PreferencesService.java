package com.news.news.server;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferencesService {
	private Context context;
	public PreferencesService(Context context) {  
		this.context = context;  
	}  

	public void saveUser(String name,String pwd,String classs,boolean a,boolean jizhu) {
		SharedPreferences preferences = context.getSharedPreferences("denglu", Context.MODE_PRIVATE);  
		Editor editor = preferences.edit();  
		editor.putString("name", name);  
		editor.putString("pwd", pwd);  
		editor.putString("email",classs);
		editor.putBoolean("boolean", a);
		editor.putBoolean("jizhu",jizhu);
		editor.commit();  
	}  
	public Map<String, Object> getUserPerferences() {
		Map<String, Object> params = new HashMap<String, Object>();  
		SharedPreferences preferences = context.getSharedPreferences("denglu", Context.MODE_PRIVATE);  
		params.put("name", preferences.getString("name", ""));  
		params.put("pwd", preferences.getString("pwd", ""));  
		params.put("classs", preferences.getString("email", ""));
		params.put("boolean", preferences.getBoolean("boolean", false));
		params.put("jizhu", preferences.getBoolean("jizhu", false));
		return params;  
	}
}
