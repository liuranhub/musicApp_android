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
	 * popupWindow�Ĳ����ļ�
	 * */
	private int layout = 0;

	/**
	 * �ⲿ��activity
	 * */
	private Activity activity = null;

	/**
	 * ���ڵ�View
	 * */
	private View popupView = null;

	/**
	 * �����������б�View
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
			throw (new IllegalArgumentException("������һ���Ϸ���layout"));
		}
		LayoutInflater inflater = LayoutInflater.from(activity);
		// ���봰�������ļ�
		popupView = inflater.inflate(layout, null);

		// ����PopupWindow����
		popupWindow = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, false);

		// ��Ҫ����һ�´˲����������߿���ʧ
		popupWindow.setBackgroundDrawable(new BitmapDrawable());

		popupWindow.setWidth(200);
		popupWindow.setHeight(250);
		// ���õ��������ߴ�����ʧ
		popupWindow.setOutsideTouchable(true);
		// ���ô˲�����ý��㣬�����޷����
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
		 * �رյ��̱߳���������UI�߳���
		 * */
		activity.runOnUiThread(new Runnable() {
			public void run() {
				Timer timer = new Timer();
				/**
				 * ���ö�ʱ���رմ���
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
