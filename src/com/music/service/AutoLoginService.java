package com.music.service;

import com.music.net.impl.ConnectObtainJson;
import com.music.util.impl.InitUtil;

import android.app.IntentService;
import android.content.Intent;

public class AutoLoginService extends IntentService {

	public AutoLoginService() {
		
		super("auto login service");
	}

	protected void onHandleIntent(Intent intent) {
		ConnectObtainJson conn = new ConnectObtainJson();
		conn.getResult(InitUtil.getServerurl()
				+ "userAction!userLogin.action?username=1&password=1");
	}

}
