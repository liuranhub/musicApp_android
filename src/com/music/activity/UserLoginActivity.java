package com.music.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.music.musicapp.R;
import com.music.net.impl.ConnectObtainJson;
import com.music.util.impl.InitUtil;
import com.music.util.impl.MusicInstrumentStatus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

public class UserLoginActivity extends Activity {
	private TextView usernameView = null;
	private TextView passwordView = null;

	private MusicInstrumentStatus musicStatus = MusicInstrumentStatus
			.getInstrumentStatus();

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.user_login_activity);
		initView();
	}

	private void initView() {
		usernameView = (TextView) findViewById(R.id.reg_username);
		passwordView = (TextView) findViewById(R.id.login_password);
		if (musicStatus.getUser().getUsername() != null) {
			usernameView.setText(musicStatus.getUser().getUsername());
			passwordView.setText(musicStatus.getUser().getPassword());
		}

	}

	public void loginButtonOnClick(View view) {

		String username = usernameView.getText().toString();
		String password = passwordView.getText().toString();

		username = username.trim();
		password = password.trim();

		if (username.length() < 1) {
			showToast("用户名为空");
		}

		if (password.length() < 1) {
			showToast("密码为空");
		}

		else {
			if (musicStatus.getUser().isLogin()
					&& username.equals(musicStatus.getUser().getUsername())) {
				if (username.equals(musicStatus.getUser().getUsername())) {
					showToast(musicStatus.getUser().getUsername() + "已经登录");
				} else {
					showToast(musicStatus.getUser().getUsername() + "密码错误");
				}
			} else {
				musicStatus.getUser().setUsername(username);
				musicStatus.getUser().setPassword(password);
				musicStatus.getUser().setLoginState(true);

				StringBuffer urlStr = new StringBuffer(InitUtil.getServerurl()
						+ "userAction!userLogin.action");
				urlStr.append('?').append("username=").append(username)
						.append('&').append("password=").append(password);
				ConnectObtainJson conn = new ConnectObtainJson();

				String json = conn.getResult(urlStr.toString());
				if (json != null) {
					try {
						JSONObject jsonarray = new JSONObject(json);
						boolean logined = jsonarray.getBoolean("login");
						if (logined) {
							musicStatus.getUser().setLoginState(true);
						} else {
							musicStatus.getUser().setLoginState(false);
						}

					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
					musicStatus.getUser().setLoginState(false);
					showToast("登录失败，网络连接不正常");
					return;
				}

				if (musicStatus.getUser().isLogin()) {
					showToast(username + "登录成功  ！");

					Intent intent = new Intent(getBaseContext(),
							MenuActivity.class);

					startActivity(intent);

					finish();
				} else {
					showToast(username + "登录失败：密码或者账号不正确！");
				}
			}
		}

		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void hideKeyboardOnClick(View view) {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		boolean isOpen = imm.isActive();
		System.out.println(isOpen);

		if (isOpen) {
			imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}

	private void showToast(String word) {
		Toast toast = Toast
				.makeText(getBaseContext(), word, Toast.LENGTH_SHORT);
		toast.setDuration(10000);
		toast.setGravity(Gravity.TOP, 0, 250);
		toast.show();
	}

	public void registerOnClick(View view) {
		Intent intent = new Intent(getBaseContext(), UserRegisterActivity.class);
		startActivity(intent);
	}
}
