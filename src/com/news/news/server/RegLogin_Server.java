package com.news.news.server;

import java.util.List;

import com.news.news.app.App;
import com.news.news.database.RegLoginDao;
import com.news.news.entity.User;

import android.content.Context;
import android.util.Log;

public class RegLogin_Server {
	private List<User> users;
	private RegLoginDao dbDao;
	public RegLogin_Server(Context context){
		dbDao=new RegLoginDao(context);
		users=dbDao.getUsers(null,null);
	}
	public String register(String name,String pwd,String email){
		for (User use:users){
			if (use.getUser().equals(name)){
				return "���˺��Ѵ��ڣ�";
			}
			if (use.getEmail().equals(email)){
				return "�������ѱ�ע�ᣡ";
			}
		}
		User user=new User();
		user.setUser(name);
		user.setPass(pwd);
		user.setEmail(email);
		try {
			dbDao.insert(user);
		}catch (Exception e){
			e.printStackTrace();
		}
		return "ע��ɹ�";
	}
	public String login(String name,String pwd){
		for (User use:users){
			if ((use.getUser().equals(name))&&(use.getPass().equals(pwd))){
				Log.i("TAG",App.user.toString());
				Log.i("TAG",use.getUser());
				App.user.setUser(use.getUser());
				App.user.setEmail(use.getEmail());
				App.user.setPass(use.getPass());
				App.user.setId(use.getId());
				return "��¼�ɹ���";
			}
			if ((use.getEmail().equals(name))&&(use.getPass().equals(pwd))){
				Log.i("TAG",App.user.toString());
				Log.i("TAG",use.getUser());
				App.user.setUser(use.getUser());
				App.user.setEmail(use.getEmail());
				App.user.setPass(use.getPass());
				App.user.setId(use.getId());
				return "��¼�ɹ���";
			}
		}
		for (User use:users){
			if(use.getUser().equals(name)){
				return "�������������";
			}
			if(use.getEmail().equals(name)){
				return "�������������";
			}
		}
		return "û�и��˺ţ���ע��";
	}

	public long forGetPwd(String name, String email) {
		for (User use:users){
			if ((use.getUser().equals(name))&&(use.getEmail().equals(email))){
				return use.getId();
			}
		}
		return -1;
	}
}
