package com.news.news.fragment;
import java.util.ArrayList;
import java.util.List;
import com.news.news.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class FramNews extends Fragment {
	private ViewPager vpFraments;
	private RadioGroup rgNews;

	private RadioButton rbRedian; // �ȵ�
	private RadioButton rbToutiao; // ͷ��
	private RadioButton rbKeji; // �Ƽ�
	private RadioButton rbYule; // ����
	private RadioButton rbYouxi; // ��Ϸ
	private RadioButton rbYingshi; // Ӱ��
	private RadioButton rbTiyu; // ����
	private RadioButton rbCaijing; // �ƾ�
	private RadioButton rbQiche; // ����
	private RadioButton rbShishang; // ʱ��
	private RadioButton rbJunshi; // ����
	private RadioButton rbLishi; // ��ʷ
	private RadioButton rbCaipiao; // ��Ʊ
	private RadioButton rbLuntan; // ��̳

	//����Frag
	private List<Fragment> newsFragments;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fram_news, null);
		// ��ʼ��
		setViews(view);

		// FragmentAdapter
		setFragmentAdapter();

		// ������
		setListeners();

		return view;
	}

	private void setFragmentAdapter() {
		newsFragments=new ArrayList<Fragment>();
	}

	private void setListeners() {

	}

	private void setViews(View view) {
		// ViewPager
		vpFraments = (ViewPager) view.findViewById(R.id.vp_news_framents);

		// Radio
		rgNews = (RadioGroup) view.findViewById(R.id.rg_news);
		rbRedian = (RadioButton) view.findViewById(R.id.rb_news_redian);
		rbToutiao = (RadioButton) view.findViewById(R.id.rb_news_toutiao);
		rbKeji = (RadioButton) view.findViewById(R.id.rb_news_keji);
		rbYule = (RadioButton) view.findViewById(R.id.rb_news_yule);
		rbYouxi = (RadioButton) view.findViewById(R.id.rb_news_youxi);
		rbYingshi = (RadioButton) view.findViewById(R.id.rb_news_yingshi);
		rbTiyu = (RadioButton) view.findViewById(R.id.rb_news_tiyu);
		rbCaijing = (RadioButton) view.findViewById(R.id.rb_news_caijing);
		rbQiche = (RadioButton) view.findViewById(R.id.rb_news_qiche);
		rbShishang = (RadioButton) view.findViewById(R.id.rb_news_shishang);
		rbJunshi = (RadioButton) view.findViewById(R.id.rb_news_junshi);
		rbLishi = (RadioButton) view.findViewById(R.id.rb_news_lishi);
		rbCaipiao = (RadioButton) view.findViewById(R.id.rb_news_caipiao);
		rbLuntan = (RadioButton) view.findViewById(R.id.rb_news_luntan);

	}
}
