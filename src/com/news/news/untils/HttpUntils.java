package com.news.news.untils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

public class HttpUntils  {
	public static InputStream getInputStream(String path) throws Exception{
		Log.i("213", path);
		URL url=new URL(path);
		HttpURLConnection http=(HttpURLConnection) url.openConnection(); 
		http.setRequestMethod("GET");
		return http.getInputStream();
	}
	public static String intoString(InputStream in) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		StringBuilder sb=new StringBuilder();
		String line=null;
		if((line=br.readLine())!=null){
			sb.append(line);
		}
		return sb.toString();
	}
}
