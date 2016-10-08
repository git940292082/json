package com.news.news.main;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import com.news.news.R;
import com.news.news.app.App;
import com.news.news.server.PreferencesService;
import com.news.news.server.RegLogin_Server;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQAuth;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
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
	private boolean jizhu;
	private Tencent mTencent;
	private ImageButton weibo;
	private ImageButton weixin;
	private ImageButton qq;
	private String mAppid = "222222";// ����ʱʹ�ã�����������ʱ��Ҫ�����Լ���APP_ID
	private QQAuth mQQAuth;
	private UserInfo mInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// 1.4�汾:�˴�����������������Ӧ�ó����ȫ��context����ͨ��activity��getApplicationContext������ȡ
		perfer=new PreferencesService(this);
		Map<String, Object> login=perfer.getUserPerferences();
		jizhu=(Boolean)login.get("jizhu");
		// 2.1. ��ʼ���������������пؼ�
		SetView();
		etUsername.setText((String)login.get("name"));
		etPassword.setText((String)login.get("pwd"));
		if (jizhu) {
			check_jizhu.setChecked(true);
		}
		//�ж��ǲ����Զ���¼
		if ((Boolean) login.get("boolean")) {
			App.user.setUser((String) login.get("name"));
			App.user.setEmail((String) login.get("email"));
			App.user.setPass((String) login.get("pwd"));
			startActivity(new Intent(LoginActivity.this, MainActivity.class));
			finish();
		}
		achieve=(TextView)findViewById(R.id.lg_tv_rg);
		forPwd=(TextView)findViewById(R.id.lg_tv_forget);
		SetOnClick();
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
	protected void onStart() {
		super.onStart();
		mQQAuth = QQAuth.createInstance(mAppid, getApplicationContext());
		mTencent = Tencent.createInstance(mAppid,getApplicationContext());
	}
	private void SetOnClick() {
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
		qq.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onClickLogin();
			}
		});
	}
	private void onClickLogin() {
		if (!mQQAuth.isSessionValid()) {
			IUiListener listener = new BaseUiListener() {
				@Override
				protected void doComplete(JSONObject values) {
					updateUserInfo();
				}
			};
			mQQAuth.login(this, "all", listener);
			// mTencent.loginWithOEM(this, "all",
			// listener,"10000144","10000144","xxxx");
			mTencent.login(this, "all", listener);
		} else {
			mQQAuth.logout(this);
			updateUserInfo();
		}
	}
	private void updateUserInfo() {
		if (mQQAuth != null && mQQAuth.isSessionValid()) {
			IUiListener listener = new IUiListener() {

				@Override
				public void onError(UiError e) {
					Toast.makeText(getApplicationContext(), "��¼ʧ��",Toast.LENGTH_SHORT).show();
				}

				@Override
				public void onComplete(final Object response) {
					Message msg = new Message();
					msg.obj = response;
					msg.what = 0;
					mHandler.sendMessage(msg);
				}

				@Override
				public void onCancel() {
				}
			};
			mInfo = new UserInfo(this, mQQAuth.getQQToken());
			mInfo.getUserInfo(listener);

		} else {
		}
	}
	private class BaseUiListener implements IUiListener {

		@Override
		public void onComplete(Object response) {
			Log.i("TAG",response.toString());
			doComplete((JSONObject) response);
		}

		protected void doComplete(JSONObject values) {

		}

		@Override
		public void onError(UiError e) {
		}

		@Override
		public void onCancel() {
		}
	}
	private void SetView() {
		// (��)2. ��ʼ���������������б�����2�������ؼ�
		btnLogin = (Button) findViewById(R.id.lg_btn_login);
		etUsername = (EditText) findViewById(R.id.lg_et_username);
		etPassword = (EditText) findViewById(R.id.lg_et_password);
		//��ס����
		check_jizhu=(CheckBox)findViewById(R.id.checkBox1);
		//�Զ���¼
		check_zidong=(CheckBox)findViewById(R.id.CheckBox2);
		qq=(ImageButton)findViewById(R.id.qq_Button);
		weixin=(ImageButton)findViewById(R.id.weixin_Button);
		weibo=(ImageButton)findViewById(R.id.weibo_Button);
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
					if (check_jizhu.isChecked()) perfer.saveUser(username, password, App.user.getEmail(),true,true);
					else perfer.saveUser(username, "",App.user.getEmail(), true,false);
				}else {
					if (check_jizhu.isChecked()) perfer.saveUser(username, password, App.user.getEmail(),false,true);
					else perfer.saveUser(username, "",App.user.getEmail(), false, false);
				}
				startActivity(new Intent(LoginActivity.this, MainActivity.class));
				finish();
			}
			mDialog.dismiss();
		}

	}

	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				JSONObject response = (JSONObject) msg.obj;
				Log.i("TAG", response.toString());
				if (response.has("nickname")) {
					try {
						Log.i("name",response.getString("nickname")); 
						App.user.setUser(response.getString("nickname"));
						startActivity(new Intent(LoginActivity.this, MainActivity.class));
						finish();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} 
		}

	};
}
