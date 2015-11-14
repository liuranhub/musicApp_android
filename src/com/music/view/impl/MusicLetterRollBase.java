package com.music.view.impl;

import java.util.LinkedList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

import com.music.musicapp.R;
import com.music.view.inter.MusicLetterRoll;

/**
 * �Ҳ����ĸ������<br>
 * ���������������
 * */
public class MusicLetterRollBase implements MusicLetterRoll {

	/**
	 * ���������ʱ��Activity,���ŵ�������Ļ�������
	 * */
	private Activity activity = null;
	/**
	 * ������ĸ��LinearLayout�б�,View���������ϵͳ����ʵ
	 * */
	private LinearLayout letters = null;

	/**
	 * ����������ĸ��RelativeLayout��������,����������ĸ����ʵ����
	 * */
	private LinkedList<RelativeLayout> letterList = new LinkedList<RelativeLayout>();

	public MusicLetterRollBase(Activity activity, LinearLayout letters) {
		this.activity = activity;
		this.letters = letters;
	}

	private void initLetters() {
		for (int i = 65; i <= 91; i++) {
			final int id = i;
			char ch = (char) i;
			String text = null;
			if (i == 91) {
				text = "?";
			} else {
				text = Character.toString(ch);
			}

			LayoutParams textLayout = new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			textLayout.addRule(RelativeLayout.CENTER_IN_PARENT);

			final TextView textView = new TextView(activity.getBaseContext());
			textView.setText(text);
			textView.setTextColor(Color.WHITE);
			textView.setTextSize(12);

			final RelativeLayout textRela = new RelativeLayout(
					activity.getBaseContext());
			textRela.setId(i);
			textRela.setBackgroundResource(R.drawable.list_letter_background_white);
			textRela.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					for (RelativeLayout letter : letterList) {
						letter.setBackgroundResource(R.drawable.list_letter_background_white);
						TextView text = (TextView) letter.getChildAt(0);
						text.setTextColor(Color.WHITE);
						text.setTextSize(12);
					}

					Intent intent = new Intent();
					intent.putExtra("word", (char) id);

					activity.sendBroadcast(intent.setAction("local_seek_to"));

					textView.setTextColor(Color.GREEN);
					textView.setTextSize(14);
				}
			});

			LayoutParams relaLayout = new LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
			relaLayout.width = 34;
			relaLayout.height = 34;

			textRela.addView(textView, textLayout);

			letters.addView(textRela, relaLayout);

			letterList.add(textRela);

		}
	}

	public LinkedList<RelativeLayout> getLetterList() {
		return letterList;
	}

	public void roduction() {
		initLetters();
	}
}
