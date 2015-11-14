package com.music.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class InitMusicService extends Service {

	public IBinder onBind(Intent intent) {
		return null;
	}

	public int onStartCommand(Intent intent, int flags, int startId) {
		
		initMusic();
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	private void initMusic(){

	
	}
	
}
