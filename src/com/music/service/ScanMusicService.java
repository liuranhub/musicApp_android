package com.music.service;

import com.music.util.impl.MusicBase;
import com.music.util.inter.Music;

import android.app.IntentService;
import android.content.Intent;

public class ScanMusicService extends IntentService{

	public ScanMusicService() {
		super("scanMusicService");
	}

	protected void onHandleIntent(Intent intent) {
		new Thread(new Runnable() {
			public void run() {
				Music music  = MusicBase.createMusic() ;
				music.scanMusic();
			}
		}).start();
		
	}

}
