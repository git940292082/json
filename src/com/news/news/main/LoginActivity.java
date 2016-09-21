package com.news.news.main;

import java.util.Map;

import com.news.news.R;
import com.news.news.app.App;
import com.news.news.server.PreferencesService;
import com.news.news.server.RegLogin_Server;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends Activity {
	// ������Ŀ�꡿
	// ������ȷ���û���������ֱ���admin | admin888
	// ���û�������û�������4λ�������벻��6λʱ����ťʱ���õ�
	// ���û���������ĳ��Ⱦ��ﵽ��׼�������ð�ť
	// �������¼����ť������֤����ʹ��Toast��ʾ���

	// ���������衿
	// 1. ����ȫ�ֱ���������¼����ť�ؼ������롰�û������͡����롱�������ؼ�
	// ����������--�¡�
	// (��)1. ����ȫ�ֱ�����2�������ؼ�
	private Button btnLogin;
	private EditText etUsername;
	private EditText etPassword;
	private TextView achieve;
	private PreferencesService perfer;
	private CheckBox check_jizhu;
	private AlertDialog mDialog;
	private CheckBox check_zidong;
	private String username;
	private String password;
	private TextView forPwd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		perfer=new PreferencesService(this);
		Map<String, Object> login=perfer.getUserPerferences();
		// 2.1. ��ʼ���������������пؼ�
		// (��)2. ��ʼ���������������б�����2�������ؼ�
		btnLogin = (Button) findViewById(R.id.lg_btn_login);
		etUsername = (EditText) findViewById(R.id.lg_et_username);
		etPassword = (EditText) findViewById(R.id.lg_et_password);
		//��ס����
		check_jizhu=(CheckBox)findViewById(R.id.checkBox1);
		//�Զ���¼
		check_zidong=(CheckBox)findViewById(R.id.CheckBox2);
		etUsername.setText((String)login.get("name"));
		etPassword.setText((String)login.get("pwd"));
		//�ж��ǲ����Զ���¼
		if ((Boolean) login.get("boolean")) {
			App.user.setUser((String) login.get("name"));
			App.user.setEmail((String) login.get("classs"));
			App.user.setPass((String) login.get("pwd"));
			startActivity(new Intent(LoginActivity.this, MainActivity.class));
			finish();
		}
		achieve=(TextView)findViewById(R.id.lg_tv_rg);
		forPwd=(TextView)findViewById(R.id.lg_tv_forget);
		forPwd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(LoginActivity.this,ForGetPwdActivity1.class));
			}
		});
		//ע�ᰴť�ĵ���¼�
		achieve.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
			}
		});
		//�Զ���¼״̬�ı�ʱ���ı��ס�����״̬
		check_zidong.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked)  check_jizhu.setChecked(true);
			}
		});
		//�ж�����Զ���¼������Ļ�����ʾ��ȡ���Զ���¼
		check_jizhu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (check_zidong.isChecked()) {
					check_jizhu.setChecked(true);
					Toast.makeText(LoginActivity.this,"��ȡ����ѡ�Զ���¼", Toast.LENGTH_SHORT).show();
				}
			}
		});
		// 2.2. ����ťĬ�Ͻ���
		btnLogin.setEnabled(false);
		String username = etUsername.getText().toString().trim();
		String password = etPassword.getText().toString();
		if (password.length() >= 3) {
			btnLogin.setEnabled(true);
		}
		// (��)4. ����������(ʵ���˽ӿڵ��ڲ���)�Ķ���
		InnerTextWatcher watcher = new InnerTextWatcher();
		// (��)5. Ϊ2����������ü�����
		etUsername.addTextChangedListener(watcher);
		etPassword.addTextChangedListener(watcher);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 4. ����������(��Ա�ڲ���)����
		InnerOnClickListener listener = new InnerOnClickListener();
		// 5. Ϊ��ť���ü�����
		btnLogin.setOnClickListener(listener);
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
			btnLogin.setEnabled(password.length() >= 3);

		}

	}

	// 3. ʹ�ó�Ա�ڲ�����﷨��ʵ��OnClickListener�ӿ�
	private class InnerOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			mDialog = new AlertDialog.Builder(LoginActivity.this).create();
			mDialog.setCanceledOnTouchOutside(false);// ���õ����ĻDialog����ʧ
			mDialog.show();
			// ע��˴�Ҫ����show֮�� ����ᱨ�쳣
			mDialog.setContentView(R.layout.loading_process_dialog_anim);
			// ������������
			// 1. ׼����ʼ����
			// -- �������ؼ��л�ȡ�û�������û���������
			username = etUsername.getText().toString().trim();
			password = etPassword.getText().toString();
			// -- �ж��û�������û����������Ƿ���ȷ������������

			String msg =new RegLogin_Server(getApplicationContext()).login(username, password);

			// 3. ��ʾ���
			// -- ʹ��Toast��ʾ���
			Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
			if (msg.equals("��¼�ɹ���")) {
				if (check_zidong.isChecked()) {
					perfer.saveUser(username, password,App.user.getEmail(),true);
				}else {
					if (check_jizhu.isChecked()) perfer.saveUser(username, password, App.user.getEmail(),false);
					else perfer.saveUser(username, "",App.user.getEmail(), false);
				}
				startActivity(new Intent(LoginActivity.this, MainActivity.class));
				finish();
			}
			mDialog.dismiss();
		}

	}

}
