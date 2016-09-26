package com.news.news.adapter;

import java.util.List;

import org.xutils.x;

import com.news.news.R;
import com.news.news.entity.FunnyItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


/**
 * FunnyFragment ���� adapter
 * @author Shark Z  
 *
 */
public class FunnyAdapter extends BaseAdapter{
	
	private List<FunnyItem> funnys;
	@SuppressWarnings("unused")
	private ListView listView;//�Ƴ�ĳһ�ߵ�ʱ�� ��Ҫ listview ��ʾ
	private LayoutInflater inflater;
	
	
	/**
	 * ���췽��
	 * @param context
	 * @param funnys
	 * @param listView
	 */
	public FunnyAdapter(Context context,List<FunnyItem> funnys,ListView listView){
		
		this.funnys=funnys;
		this.listView=listView;
		inflater=LayoutInflater.from(context);
		
		
	}
	

	@Override
	public int getCount() {
		return funnys.size();
	}
	@Override
	public FunnyItem getItem(int position) { 
		return funnys.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	
	//ת�� View  
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder=null;
		
		if(convertView==null){
			
			//��ʼ���ؼ�
			holder=new ViewHolder();
			convertView=inflater.inflate(R.layout.item_funny_fragment_list, null);
			
			holder.tvTitle=(TextView) convertView.findViewById(R.id.tv_title_item_funny);
			holder.ivFunnyPic=(ImageView) convertView.findViewById(R.id.iv_funny_pic_item_funny);
			
			convertView.setTag(holder);
			
		}
		
		//��ֵ
		FunnyItem funny = funnys.get(position);
		 
		holder=(ViewHolder) convertView.getTag();
		//--����
		holder.tvTitle.setText(funny.getContent());
		
		
		/**
		 * �������ͼƬ�ؼ�  Ҳ���Ի��� webView �ؼ� ֱ���õ� ͼƬ URL  ��ʾ ͼƬ 
		 */
		//--ͼƬ
		x.image().bind(holder.ivFunnyPic, funny.getUrl());
		
		
		
		//����
		return convertView;
	}
	
	/**
	 * Adapter�Ż�  ������
	 * @author Administrator
	 *
	 */
	class ViewHolder {
		TextView tvTitle;
		ImageView ivFunnyPic;
	}
	

}
