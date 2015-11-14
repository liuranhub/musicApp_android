package com.music.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.music.musicapp.R;
import com.music.net.impl.ConnectObtainJson;
import com.music.util.impl.InitUtil;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class UserRegisterActivity extends Activity {


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.user_register_activity);
	}

	public void registerOnClick(View view) {
		String username = ((TextView) findViewById(R.id.reg_username))
				.getText().toString().trim();
		String password = ((TextView) findViewById(R.id.reg_password))
				.getText().toString().trim();
		String repassword = ((TextView) findViewById(R.id.reg_repassword))
				.getText().toString().trim();

		String phone = ((TextView) findViewById(R.id.reg_phone)).getText()
				.toString().trim();
		String mail = ((TextView) findViewById(R.id.reg_mail)).getText()
				.toString().trim();

		if (username == null) {
			Toast.makeText(getBaseContext(), "用户名为空", Toast.LENGTH_LONG).show();
			return;
		}
		// if (password.equals(repassword)) {
		// System.out.println(password);
		// System.out.println(repassword);
		// Toast.makeText(getBaseContext(), "密码不相同", Toast.LENGTH_LONG).show();
		// return;
		// }

		StringBuffer urlStr = new StringBuffer(InitUtil.getServerurl()
				+ "userAction!userRegister.action");
		urlStr.append('?').append("username=").append(username).append('&')
				.append("password=").append(password).append('&')
				.append("phoneNumber=").append(phone).append('&')
				.append("email=").append(mail);
		ConnectObtainJson conn = new ConnectObtainJson();

		String json = conn.getResult(urlStr.toString());

		if (json != null) {
			try {
				JSONObject jsonarray = new JSONObject(json);
				boolean registered = jsonarray.getBoolean("registered");
				if (registered) {
					Toast.makeText(getBaseContext(), "注册成功", Toast.LENGTH_LONG)
							.show();
					finish();
				} else {
					Toast.makeText(getBaseContext(), "注册失败", Toast.LENGTH_LONG)
							.show();
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

	}
}
