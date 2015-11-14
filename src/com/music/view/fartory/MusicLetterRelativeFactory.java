package com.music.view.fartory;

import java.util.List;

import android.app.Activity;

import com.music.view.impl.MusicLetterRelativeBase;
import com.music.view.inter.MusicLetterRelative;

public class MusicLetterRelativeFactory {
	public MusicLetterRelativeFactory() {

	}

	public MusicLetterRelative create(Activity activity, String word,
			List<Integer> list){
		return new MusicLetterRelativeBase(activity, word, list) ;
	}
}
