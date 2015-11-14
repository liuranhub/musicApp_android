package com.music.service;

import com.music.util.impl.MusicBase;
import com.music.util.inter.Music;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MusicPlayerService extends Service {

	private Music music = MusicBase.createMusic();

	public MusicPlayerService() {

	}

	public IBinder onBind(Intent intent) {
		return null;
	}

	public int onStartCommand(Intent intent, int flags, int startId) {

		/**
		 * 前台运行
		 * */
		startForeground(0, null);
		
		if (intent != null) {
			int type = intent.getIntExtra("type", 99);
			if (type == 0) {
				music.start();
			} else if (type == 1) {
				music.stop();
			} else if (type == 2) {
				music.next();
			} else if (type == 3) {
				music.previous();
			} else if (type == 4) {
				int index = intent.getIntExtra("index", 1);
				music.startFromIndex(index);
			} else if (type == 5) {
				music.autoMusic();
			}

			if (type != 1)
				this.sendBroadcast(new Intent().setAction("change_name"));
		}
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		music.stop();
	}

}
