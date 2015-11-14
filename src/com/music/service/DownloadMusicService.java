package com.music.service;

import com.music.net.impl.ConnectDownLoadMusic;

import android.app.IntentService;
import android.content.Intent;

public class DownloadMusicService extends IntentService {

	public DownloadMusicService() {
		super("download");
	}

	@Override
	protected void onHandleIntent(Intent intent) {

		String urlStr = intent.getStringExtra("urlStr");

		String musicName = intent.getStringExtra("musicName");
		if (musicName == null) {
			musicName = "unknow";
		}
		ConnectDownLoadMusic conn = new ConnectDownLoadMusic();
		conn.setMusicName(musicName);
		conn.getResult(urlStr);

		/**
		 * 下载完成后发送广播
		 * */
		sendBroadcast(new Intent().setAction("downloaded"));

		startService(new Intent(getBaseContext(), ScanMusicService.class));

	}

}
