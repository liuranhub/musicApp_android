package com.music.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.music.musicapp.R;

public class DownloadActivity extends Activity {

	/**
	 * 显示所有下载的歌曲的Activity（包括正在下载和已经下载）
	 * */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.download_activity);
	}

}
