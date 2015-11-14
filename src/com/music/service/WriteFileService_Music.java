package com.music.service;

import com.music.serializable.MusicBean;
import com.music.util.impl.InitUtil;
import com.music.util.impl.MusicBase;
import com.music.util.impl.SerializUtil;

import android.app.IntentService;
import android.content.Intent;

public class WriteFileService_Music extends IntentService {

	public WriteFileService_Music() {
		super("writeFileService");
	}

	protected void onHandleIntent(Intent intent) {
		MusicBase music = MusicBase.createMusic();
		MusicBean bean = new MusicBean();
		bean.setCurrentId(music.getCurrentMusicInfor().getLocalId());
		bean.setMusicList(music.getMusicList());
		new SerializUtil().writeMusicBean(bean, InitUtil.getProfile_Music());
	}

}
