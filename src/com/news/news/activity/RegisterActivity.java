package com.news.news.activity;
import org.json.JSONException;
import org.json.JSONObject;

import com.news.news.R;
import com.news.news.server.RegLogin_Server;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	// ������Ŀ�꡿
	// ������ȷ���û���������ֱ���admin | admin888
	// ���û�������û�������4λ�������벻��6λʱ����ťʱ���õ�
	// ���û���������ĳ��Ⱦ��ﵽ��׼�������ð�ť
	// �������¼����ť������֤����ʹ��Toast��ʾ���

	// ���������衿
	// 1. ����ȫ�ֱ���������¼����ť�ؼ������롰�û������͡����롱�������ؼ�
	// ����������--�¡�
	// (��)1. ����ȫ�ֱ�����2�������ؼ�
	private Button btnAchieve;
	private EditText etUsername;
	private EditText etPassword;
	private EditText etRePassword;
	private TextView title_mid;
	private ImageView title_left;
	private EditText etEmail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		//���ñ�����--start
		//��������ʼ
		title_mid=(TextView)findViewById(R.id.title_mid);
		title_mid.setText("���û�ע��");
		title_left=(ImageView)findViewById(R.id.title_left);
		title_left.setVisibility(View.VISIBLE);
		//����������
		title_left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		//���ñ�����--end
		// 2.1. ��ʼ���������������пؼ�
		// (��)2. ��ʼ���������������б�����2�������ؼ�
		btnAchieve = (Button) findViewById(R.id.rg_btn_achieve);
		etUsername = (EditText) findViewById(R.id.rg_et_username);
		etEmail = (EditText) findViewById(R.id.rg_et_email);
		etPassword = (EditText) findViewById(R.id.rg_et_password);
		etRePassword = (EditText) findViewById(R.id.rg_et_repassword);
		// 2.2. ����ťĬ�Ͻ���
		btnAchieve.setEnabled(false);

		// 4. ����������(��Ա�ڲ���)����
		InnerOnClickListener listener = new InnerOnClickListener();
		// (��)4. ����������(ʵ���˽ӿڵ��ڲ���)�Ķ���
		InnerTextWatcher watcher = new InnerTextWatcher();

		// 5. Ϊ��ť���ü�����
		btnAchieve.setOnClickListener(listener);
		// (��)5. Ϊ2����������ü�����
		etUsername.addTextChangedListener(watcher);
		etPassword.addTextChangedListener(watcher);
		etRePassword.addTextChangedListener(watcher);
	}

	// (��)3. �Զ����ڲ��࣬ʵ��TextWatcher�ӿ�
	private class InnerTextWatcher implements TextWatcher {

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
		}

		@Override
		public void afterTextChanged(Editable s) {
			// (��)��ʵ�ּ���������
			String username = etUsername.getText().toString().trim();
			String password = etPassword.getText().toString();
			String repassword = etRePassword.getText().toString();
			btnAchieve.setEnabled( password.length() >= 3&& repassword.length() >= 3);

		}

	}

	// 3. ʹ�ó�Ա�ڲ�����﷨��ʵ��OnClickListener�ӿ�
	private class InnerOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// ������������
			// 1. ׼����ʼ����
			// -- �������ؼ��л�ȡ�û�������û���������
			String username = etUsername.getText().toString().trim();
			String email = etEmail.getText().toString().trim();
			String password = etPassword.getText().toString().trim();
			String repassword = etRePassword.getText().toString().trim();
			String msg;

			// -- �ж��û�������û����������Ƿ���ȷ������������
			if (password.equals(repassword)) {
				RegLogin_Server server=new RegLogin_Server(RegisterActivity.this);
				String s=server.register(username,password,email);
				msg=s;
			} else {
				msg = "�����������벻һ��";
			}

			// 3. ��ʾ���
			// -- ʹ��Toast��ʾ���
			Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
			if(msg.equals("ע��ɹ�")){
				startActivity(new Intent(RegisterActivity.this,MainActivity.class));
				finish();
			}
		}

	}
}
