package com.news.news.view;

import java.util.List;

import com.news.news.entity.Funny;
import com.news.news.entity.FunnyItem;





/**
 *FunnyFragment View �� 
 * @author Shark Z 
 *
 */
public interface FunnyView extends NewsView{
	
	
	
	/**
	 * ��ʾFunnyFragment list���ϵķ���
	 */
	public void showFunnyTextList(List<FunnyItem> funnys);

}
