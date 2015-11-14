package com.music.view.impl;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import com.music.musicapp.R;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class ModePopupWindow {

	private PopupWindow popupWindow = null;
	/**
	 * popupWindow的布局文件
	 * */
	private int layout = 0;

	/**
	 * 外部的activity
	 * */
	private Activity activity = null;

	/**
	 * 窗口的View
	 * */
	private View popupView = null;

	/**
	 * 窗口中所有列表View
	 * */
	private ArrayList<View> viewList = new ArrayList<View>();

	public ModePopupWindow(Activity activity, int layout) {
		this.activity = activity;
		this.layout = layout;
		initPopupWindow();
	}

	private void initPopupWindow() {
		if (activity == null) {
			throw (new NullPointerException());
		}
		if (layout == 0) {
			throw (new IllegalArgumentException("必须是一个合法的layout"));
		}
		LayoutInflater inflater = LayoutInflater.from(activity);
		// 引入窗口配置文件
		popupView = inflater.inflate(layout, null);

		// 创建PopupWindow对象
		popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, false);

		// 需要设置一下此参数，点击外边可消失
		popupWindow.setBackgroundDrawable(new BitmapDrawable());

		popupWindow.setWidth(200);
		popupWindow.setHeight(250);
		// 设置点击窗口外边窗口消失
		popupWindow.setOutsideTouchable(true);
		// 设置此参数获得焦点，否则无法点击
		popupWindow.setFocusable(true);

	}

	public void showAsDropDown(View view) {
		if (view == null) {
			return;
		}
		popupWindow.showAsDropDown(view);
	}

	public void showAsDropDown(View view, int x, int y) {
		popupWindow.showAsDropDown(view, x, y);
	}

	public void setWidth(int width) {
		popupWindow.setWidth(width);
	}

	public void setHight(int height) {
		popupWindow.setHeight(height);
	}

	public PopupWindow getPopupWindow() {
		return popupWindow;
	}

	public boolean isShowing() {
		return popupWindow.isShowing();
	}

	public void dismiss() {
		popupWindow.dismiss();
	}

	public View getView() {
		return popupView;
	}

	public View findViewById(int id) {
		return popupView.findViewById(id);
	}

	public void setOnClickListenerToView(int id, OnClickListener listener) {
		this.findViewById(id).setOnClickListener(listener);
	}

	public void updateStartPoint(View view, int xoff, int yoff) {
		popupWindow.update(view, xoff, yoff, popupWindow.getWidth(),
				popupWindow.getHeight());
	}

	public ModePopupWindow addViewListElement(View view) {
		viewList.add(view);
		return this;
	}

	public View getViewListElement(int index) {
		if (index < viewList.size()) {
			return viewList.get(0);
		} else if (index > viewList.size()) {
			return viewList.get(viewList.size() - 1);
		} else {
			return viewList.get(index);
		}
	}

	public void changeColor(int id) {

		int count = 0;
		for (View view : viewList) {
			count++;
			LinearLayout linear = (LinearLayout) view;
			TextView textView = (TextView) linear.getChildAt(1);
			ImageView imageView = (ImageView) linear.getChildAt(0);
			if (count == id) {
				textView.setTextColor(Color.rgb(0, 255, 255));
				if (id == 1) {
					imageView
							.setImageResource(R.drawable.bf_playmode_repeate_random_clear);
				} else if (id == 2) {
					imageView
							.setImageResource(R.drawable.bf_playmode_repeate_all_clear);
				} else {
					imageView
							.setImageResource(R.drawable.bf_playmode_repeate_single_clear);
				}
			} else {
				if (count == 1) {
					imageView
							.setImageResource(R.drawable.bf_playmode_repeate_random);
				} else if (count == 2) {
					imageView
							.setImageResource(R.drawable.bf_playmode_repeate_all);
				} else {
					imageView
							.setImageResource(R.drawable.bf_playmode_repeate_single);
				}
				textView.setTextColor(Color.rgb(255, 255, 255));
			}
		}
	}

	public void autoClosePopupWindow(final int time) {
		/**
		 * 关闭的线程必须运行在UI线程上
		 * */
		activity.runOnUiThread(new Runnable() {
			public void run() {
				Timer timer = new Timer();
				/**
				 * 是用定时器关闭窗口
				 * */
				timer.schedule(new TimerTask() {
					public void run() {
						if (isShowing()) {
							dismiss();
						}
					}
				}, time);
			}
		});
	}

}
