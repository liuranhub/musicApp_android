package com.music.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class UpdateOrderService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		this.sendBroadcast(new Intent().setAction("updata_order_windows"));
		return super.onStartCommand(intent, flags, startId);
	}

}
