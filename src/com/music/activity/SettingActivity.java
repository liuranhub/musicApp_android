package com.music.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.music.musicapp.R;
import com.music.service.ScanMusicService;

public class SettingActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.setting_activity);

	}

	public void scanMusicOnClick(View view) {
		startService(new Intent(getBaseContext(), ScanMusicService.class));
	}
}
