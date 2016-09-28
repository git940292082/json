package com.news.news.fragment;

import java.util.ArrayList;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import com.news.news.R;
import com.news.news.fragment.news.NewsFragment;
import com.news.news.fragment.news.ToutiaoFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class FramNews extends Fragment {
	@ViewInject(R.id.vp_news_framents)
	private ViewPager vpFraments;
	@ViewInject(R.id.rg_news)
	private RadioGroup rgNews;
	@ViewInject(R.id.rb_news_toutiao)
	private RadioButton rbToutiao; // ͷ��
	@ViewInject(R.id.rb_news_keji)
	private RadioButton rbKeji; // �Ƽ�
	@ViewInject(R.id.rb_news_yule)
	private RadioButton rbYule; // ����
	@ViewInject(R.id.rb_news_tiyu)
	private RadioButton rbTiyu; // ����
	@ViewInject(R.id.rb_news_caijing)
	private RadioButton rbCaijing; // �ƾ�
	@ViewInject(R.id.rb_news_shishang)
	private RadioButton rbShishang; // ʱ��
	@ViewInject(R.id.rb_news_junshi)
	private RadioButton rbJunshi; // ����
	@ViewInject(R.id.rb_news_shehui)
	private RadioButton rbShehui; // ���
	@ViewInject(R.id.rb_news_guonei)
	private RadioButton rbGuonei; // ����
	@ViewInject(R.id.rb_news_guoji)
	private RadioButton rbGuoji; // ����

	// ����Frag
	private List<Fragment> newsFragments;
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view == null) {
			view = inflater.inflate(R.layout.fram_news, null);
			// xע��
			x.view().inject(this, view);
			// FragmentAdapter
			setFragmentAdapter();
			// ������
			setListeners();
		} else {
			((ViewGroup) view.getParent()).removeView(view);
		}
		return view;
	}

	private void setFragmentAdapter() {
		newsFragments = new ArrayList<Fragment>();
		newsFragments.add(new ToutiaoFragment());
		newsFragments.add(new NewsFragment("shehui"));
		newsFragments.add(new NewsFragment("guonei"));
		newsFragments.add(new NewsFragment("guoji"));
		newsFragments.add(new NewsFragment("yule"));
		newsFragments.add(new NewsFragment("tiyu"));
		newsFragments.add(new NewsFragment("junshi"));
		newsFragments.add(new NewsFragment("keji"));
		newsFragments.add(new NewsFragment("caijing"));
		newsFragments.add(new NewsFragment("shishang"));
		

	}

	private void setListeners() {

	}
}
