package com.music.util.impl;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeSet;

import android.app.Activity;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class MusicScrollLyric {
	private ScrollView scroll = null;

	private LinearLayout linear = null;

	private Activity activity = null;

	/**
	 * lrcMap ��Lrc�е�һ�����ԣ�Ϊ�˷�������������ó�����
	 * */
	private Map<Integer, String> lrcMap = null;

	private Lrc lyric = null;

	private LinkedList<TextView> textList = new LinkedList<TextView>();

	private int lastIndex = -1;

	public MusicScrollLyric(ScrollView scroll, Activity activity) {
		this.scroll = scroll;
		this.activity = activity;
		linear = (LinearLayout) scroll.getChildAt(0);
	}

	public void setLyric(Lrc lyric) {

		this.lrcMap = lyric.getLrcMap();

		this.lyric = lyric;

		linear.removeAllViews();

		for (int i = 0; i < 6; i++) {
			initOneLine("");
		}

		for (Integer key : lrcMap.keySet()) {
			String lrc = lrcMap.get(key);
			initOneLine(lrc);
		}

		initOneLine("");
	}

	/**
	 * ����������
	 * */
	public void clearScroll() {
		lrcMap = null;
		lyric = null;
		textList.clear();
		linear.removeAllViews();
		lastIndex = -1;
	}

	/**
	 * �ⲿ���÷�������
	 * 
	 * @param currentTime
	 *            ��ǰ���ֲ������ڵ�ʱ�䣬�Ժ���Ϊ��λ
	 * */
	public void scrollTo(int currentTime) {
		/**
		 * ���и�ʵ�ʱ����ܸ����е���
		 * */
		if (lyric != null) {
			int time = currentTime + lyric.getOffset();
			scrollToPoint((time / 1000));
		}
	}

	/**
	 * ��ʵĹ���
	 * */
	private void scrollToPoint(final int currentTime) {

		if (lrcMap != null) {
			TreeSet<Integer> set = new TreeSet<Integer>(lrcMap.keySet());

			Object[] array = set.toArray();

			for (int i = 0; i < array.length - 1; i++) {
				final int index = i;
				Integer first = (Integer) array[i];
				Integer second = (Integer) array[i + 1];

				/**
				 * �ж�ǰn-1���Ƿ�����Ҫ��
				 * */
				if (currentTime < second && currentTime > first
						&& lastIndex != i) {
					lastIndex = i;
					change(index);
					break;
				}

				/**
				 * �ж��Ƿ����һ���Ƿ�����Ҫ��
				 * */
				if ((Integer) (array[array.length - 1]) < currentTime) {
					change(index + 1);
				}

			}
		}

	}

	private void change(final int index) {
		activity.runOnUiThread(new Runnable() {
			public void run() {
				for (TextView view : textList) {
					view.setTextSize(17);
					view.setTextColor(Color.rgb(190, 190, 190));
				}
				TextView textView2 = textList.get(index + 6);
				textView2.setTextSize(20);
				textView2.setTextColor(Color.rgb(30, 240, 150));
				scroll.scrollTo(0, (index + 1) * 50);
			}
		});

	}

	/**
	 * �����ʾ�Ĵ���
	 * */
	private void initOneLine(String lrc) {
		RelativeLayout textRela = new RelativeLayout(activity.getBaseContext());
		textRela.setPadding(0, 0, 0, 0);
		LayoutParams textRelaLayout = new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		textRelaLayout.height = 50;

		TextView textView = new TextView(activity.getBaseContext());
		textView.setPadding(0, 0, 0, 0);
		textView.setText(lrc);
		textView.setTextColor(Color.rgb(190, 190, 190));
		textView.setTextSize(17);

		LayoutParams textLayout = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);

		textLayout.addRule(RelativeLayout.CENTER_IN_PARENT);

		textRela.addView(textView, textLayout);

		textList.add(textView);

		linear.addView(textRela, textRelaLayout);
	}

	
	
}
