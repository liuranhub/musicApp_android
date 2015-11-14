package com.music.adapter;

import java.util.List;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class PlayerViewPagerAdapter extends PagerAdapter{
	public List<View> myListViews;

	public PlayerViewPagerAdapter(List<View> mListViews) {
		this.myListViews = mListViews;
	}

	public void destroyItem(View arg0, int arg1, Object arg2) {
		((ViewPager) arg0).removeView(myListViews.get(arg1));
	}

	public int getCount() {
		return myListViews.size();
	}

	public Object instantiateItem(View arg0, int arg1) {
		((ViewPager) arg0).addView(myListViews.get(arg1), 0);
		return myListViews.get(arg1);
	}

	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == (arg1);
	}

	public Parcelable saveState() {
		return null;
	}

}
