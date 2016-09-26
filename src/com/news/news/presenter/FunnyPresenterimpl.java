package com.news.news.presenter;

import java.util.List;

import com.news.news.entity.Funny;
import com.news.news.entity.FunnyItem;
import com.news.news.model.CommonCallback;
import com.news.news.model.impl.FunnyModelimpl;
import com.news.news.view.FunnyView;





/**
 * FunnyFragment presenter �� �ӿ� ʵ����
 * @author Shark Z  
 *
 */
public class FunnyPresenterimpl implements FunnyPresenter{

	private FunnyView view;
	private FunnyModelimpl model;
	
	/**
	 * ���췽��  �����ݺͿؼ� �������
	 */
	public FunnyPresenterimpl(FunnyView view){
		this.view=view;
		model = new FunnyModelimpl();
		
		
	}
	
	
	@Override
	public void getAllFunnys() {
		model.getAllFunnys(new CommonCallback() {
			
			@Override
			public void onDataLoaded(Object obj) {
				//1.���ݼ�������Զ����ûص����� 
				@SuppressWarnings("unchecked")
				List<FunnyItem> funnys=(List<FunnyItem>) obj;
				
				//2.��ʾ����
				view.showFunnyTextList(funnys);
				
				
			}
		});
		
		
		
		
		
	}

}
