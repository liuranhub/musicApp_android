package com.music.service;

import com.music.enums.PlayerEnum;
import com.music.util.impl.MusicBase;
import com.music.util.impl.MusicInstrumentStatus;
import com.music.util.inter.Music;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AutoPlayService extends Service {

	private boolean status = true;
	private boolean createThread = true;
	private MusicInstrumentStatus musicStatus = MusicInstrumentStatus
			.getInstrumentStatus();
	private Music music = MusicBase.createMusic();

	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		startForeground(0, null);

		/**
		 * createThread保存多次调用onStarCommand却只创建一个监听线程
		 * */
		if (createThread) {
			createThread = false;
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (status) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						if (musicStatus.isPlaying()
								&& !music.getPlayer().isPlaying()) {
							Intent intent = new Intent(getBaseContext(),
									MusicPlayerService.class);
							intent.putExtra("type", PlayerEnum.AUTO.ordinal());
							startService(intent);
							try {
								Thread.sleep(10000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}

					}
				}
			}).start();

		}
		return super.onStartCommand(intent, flags, startId);
	}

	public void onDestroy() {
		status = false;
		super.onDestroy();
	}
}
