package com.music.view.fartory;

import android.app.Activity;
import android.widget.LinearLayout;

import com.music.view.impl.MusicLetterRollBase;
import com.music.view.inter.MusicLetterRoll;

public class MusicLetterRollFactory {
	public MusicLetterRoll create(Activity activity, LinearLayout letters){
		return new MusicLetterRollBase(activity, letters);
	}
}
