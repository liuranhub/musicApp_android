package com.music.view.fartory;

import android.app.Activity;

import com.music.view.impl.MusicRowRealtiveBase;
import com.music.view.inter.MusicRowRelative;

public class MusicRowRelativeBaseFactory {
	public MusicRowRelativeBaseFactory() {

	}

	public MusicRowRelative create(Activity activity, Integer id) {
		return new MusicRowRealtiveBase(activity, id);
	}
}
