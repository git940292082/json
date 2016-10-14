package com.news.news.fragment;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.news.news.R;
import com.news.news.activity.FunnyFragment_Item_To_Activity;
import com.news.news.adapter.FunnyAdapter;
import com.news.news.entity.FunnyItem;
import com.news.news.presenter.FunnyPresenterimpl;
import com.news.news.view.FunnyView;

/**
 * ��Ц�� Ȥͼ������
 * 
 * @author SharkZ 2016-9-25
 * 
 */

// @ContentView(R.layout.fram_funny)
public class FramFunny extends Fragment implements FunnyView {

	private View view;
	private FunnyPresenterimpl presenter;
	private List<FunnyItem> funnys;

	// @ViewInject(R.id.lv_fram_funny)
	private ListView listView;

	private FunnyAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (view == null) {

			// 1.View
			view = inflater.inflate(R.layout.fram_funny, null);
			Log.i("123", "FramFunny");

			listView = (ListView) view.findViewById(R.id.lv_fram_funny);

			// //xUtils
			// x.view().inject(getActivity());

			// 2.���� presenter ����
			presenter = new FunnyPresenterimpl(this);
			// --���
			presenter.getAllFunnys();

			// 3.���ü����� ���ÿһ��Item ������ת�� һ����ʵ������Ϣ�� Activity
			setListener();

		} else {
			((ViewGroup) view.getParent()).removeView(view);

			//��ʼ����������д
			
		}
		return view;
	}

	//-------------------------------------------------------------------------------------------------------

	/**
	 * ���ּ�����
	 */
	private void setListener() {

		/**
		 * ���ÿһ����ת��һ��Activity ��ʵ������ Funny ��Ϣ
		 */
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				FunnyItem funny = funnys.get(position);

				Intent intent = new Intent(getActivity(),
						FunnyFragment_Item_To_Activity.class);
				intent.putExtra("funny", funny);

				startActivity(intent);

			}
		});

		/**
		 * Ϊlistview ���� ���� ˢ��
		 */
		listView.setOnScrollListener(new OnScrollListener() {

			boolean isBottom=false;
			boolean  isRequest=false;

			// ������ת̬�ı��ʱ��
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {

				switch (scrollState) {

				case OnScrollListener.SCROLL_STATE_IDLE://���е�ʱ��
					//�ж�
					if(isBottom&&!isRequest){

						isRequest=true;//�ظ���������

						//����presenter�����¼�������
						//						presenter.getAllFunnys(this.funnys.size(),20);

						/**
						 * 1.�������ײ� ���·�����
						 * 2.����ȡ���� ���� ȫ�����뵽 �ɼ�����
						 * ���������Ǻ��������������
						 * funnys.addAll(funnys);
						 * size()��֮ǰ��2��
						 * 
						 * 3.���� adapter
						 * adapter.notifyDataSetChanged();
						 * 
						 * 4.isRequest=true;
						 * 
						 * 5.�� �������ݵ�ҵ���߼��� ���Լ���progressBar
						 * �����û����ڸ�ϸ����
						 * 
						 */





					}

					break;
				case OnScrollListener.SCROLL_STATE_FLING://���ٻ���
					break;
				case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL://����ʱ��
					break;
				}
			}

			@Override
			public void onScroll(AbsListView view,
					int firstVisibleItem,//��һ���ɼ���item��position
					int visibleItemCount,//�ɼ���item������
					int totalItemCount) {//�ܹ��ж��ٸ�item

				if(firstVisibleItem+visibleItemCount==totalItemCount){
					//��������
					isBottom=true;
				}else{
					isBottom=false;
				}

			}
		});

	}
	//-------------------------------------------------------------------------------------------------------





	@Override
	public void showFunnyTextList(List<FunnyItem> funnys) {

		// 1.��������
		this.funnys = funnys;
		Log.i("funnys-->fragment", "funnys=" + funnys);// ͨ����־����-->�����Դ��ݹ��� ����������
		// ����adapter

		// 2.����Adapter
		/**
		 * ���� context funnys listview-->�첽����ͼƬ -->��Ҫ adapter ģ��
		 */
		adapter = new FunnyAdapter(getActivity(), funnys, listView);
		// --
		listView.setAdapter(adapter);

	}
}
