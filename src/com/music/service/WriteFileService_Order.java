package com.music.service;

import com.music.util.impl.InitUtil;
import com.music.util.impl.MusicBase;
import com.music.util.impl.MusicOrderMap;
import com.music.util.impl.SerializUtil;

import android.app.IntentService;
import android.content.Intent;

public class WriteFileService_Order extends IntentService {

	public WriteFileService_Order() {
		super("writeFileService");
	}

	protected void onHandleIntent(Intent intent) {
		MusicOrderMap map = MusicBase.createMusic().getOrderMap();
		new SerializUtil().writeMusicBean(map, InitUtil.getProfile_Order());
	}

}
