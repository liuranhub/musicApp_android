package com.music.view.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class MusicViewPager extends ViewPager {

	public MusicViewPager(Context context) {
		super(context);
	}

	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		return false;
	}

	@SuppressLint("ClickableViewAccessibility")
	public boolean onTouchEvent(MotionEvent arg0) {
		return false;
	}

	public boolean executeKeyEvent(KeyEvent event) {
		return false;
	}

}
