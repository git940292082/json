package com.news.news.entity;

import java.util.List;

/**
 * �������������ÿһ���Funny �� �м� ���� 
 * 
 * @author Shark Z
 * 
 */
public class FunnyData {

	private List<FunnyItem> data;

	public FunnyData() {
		super();
	}

	public FunnyData(List<FunnyItem> data) {
		super();
		this.data = data;
	}

	public List<FunnyItem> getData() {
		return data;
	}

	public void setData(List<FunnyItem> data) {
		this.data = data;
	}

	

}
