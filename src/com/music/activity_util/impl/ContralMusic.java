package com.music.activity_util.impl;

import com.music.activity_util.inter.ContralMusicIf;
import com.music.enums.PlayerEnum;
import com.music.service.MusicPlayerService;

import android.app.Activity;
import android.content.Intent;

public class ContralMusic implements ContralMusicIf{

	private Activity activity = null;

	public ContralMusic(Activity activity) {
		this.activity = activity;
	}

	public void startMusicPlayerService(int type) {
		Intent intent = new Intent(activity.getBaseContext(),
				MusicPlayerService.class);
		intent.putExtra("type", type);
		activity.startService(intent);
	}

	public void startMusicPlayerServiceIndex(int index) {
		Intent intent = new Intent(activity.getBaseContext(),
				MusicPlayerService.class);
		intent.putExtra("type", PlayerEnum.INDEX.ordinal());
		intent.putExtra("index", index);
		activity.startService(intent);

	}
}
