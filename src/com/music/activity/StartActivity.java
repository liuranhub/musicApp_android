package com.music.activity;

import java.util.Timer;
import java.util.TimerTask;

import com.music.musicapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class StartActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.start_activity);
	}

	protected void onResume() {
		super.onResume();
		new Timer().schedule(new TimerTask() {
			public void run() {
				startActivity(new Intent(getBaseContext(), MenuActivity.class));
			}
		}, 100);

	}

}
