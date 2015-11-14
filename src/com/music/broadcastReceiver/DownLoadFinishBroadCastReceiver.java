package com.music.broadcastReceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class DownLoadFinishBroadCastReceiver extends BroadcastReceiver {

	private Activity activity = null;

	public DownLoadFinishBroadCastReceiver(Activity activity) {
		activity = this.activity;
	}

	@Override
	public void onReceive(Context context, Intent intent) {

		Toast.makeText(activity.getBaseContext(), "œ¬‘ÿÕÍ≥…", Toast.LENGTH_LONG)
				.show();
	}

}
