package com.music.view.fartory;

import android.app.Activity;

import com.music.view.impl.MusicRowRealtivePlayer;
import com.music.view.inter.MusicRowRelative;

public class MusicRowRelativePlayerFactory {
	public MusicRowRelativePlayerFactory() {

	}

	public MusicRowRelative create(Activity activity, Integer id) {
		return new MusicRowRealtivePlayer(activity, id);
	}
}
