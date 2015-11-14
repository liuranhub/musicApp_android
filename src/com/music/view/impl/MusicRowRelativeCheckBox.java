package com.music.view.impl;

import java.util.LinkedList;

import com.music.util.impl.MusicBase;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class MusicRowRelativeCheckBox extends MusicRowRealtiveBase {

	private CheckBox checkBox = null;

	/**
	 * 歌单中得所有歌曲
	 * */
	private LinkedList<Integer> list = null;

	public MusicRowRelativeCheckBox(Activity activity, Integer id,
			String orderName) {
		super(activity, id);
		list = MusicBase.createMusic().getOrderMap().getOrder(orderName);
	}

	protected void createFirstButton() {
		checkBox = new CheckBox(getActivity().getBaseContext());
		if (list.contains(getId())) {
			checkBox.setChecked(true);
		}

		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					list.add(getId());
				} else {
					list.remove(getId());
				}
			}
		});

		LayoutParams checkBoxLayout = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		checkBoxLayout.addRule(RelativeLayout.CENTER_VERTICAL);

		getMusicRela().addView(checkBox, checkBoxLayout);
	}

	protected void createText() {
		/**
		 * musicName
		 * */
		TextView musicName = new TextView(getActivity().getBaseContext());
		musicName.setText(getMusicInf().getMusicName());
		musicName.setTextSize(19);
		musicName.setTextColor(Color.BLACK);
		musicName.setSingleLine();
		musicName.setWidth(480);
		musicName.setTextColor(Color.WHITE);
		LayoutParams musicNameLayout = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		musicNameLayout.setMargins(0, 5, 0, 0);

		/**
		 * singerName
		 * */
		TextView singerName = new TextView(getActivity().getBaseContext());
		singerName.setText("<singer unknow>");
		singerName.setTextSize(16);
		singerName.setTextColor(Color.WHITE);
		LayoutParams singerNameLayout = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		singerNameLayout.setMargins(0, 50, 0, 0);

		/**
		 * centerTextRela文字
		 * */
		RelativeLayout centerTextRela = new RelativeLayout(getActivity()
				.getBaseContext());
		centerTextRela.setId(getId());
		centerTextRela.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (checkBox.isChecked()) {
					checkBox.setChecked(false);
				} else {
					checkBox.setChecked(true);
				}
			}
		});

		centerTextRela.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				return false;
			}

		});
		LayoutParams centerTextRelaLayout = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
		centerTextRelaLayout.setMargins(75, 0, 0, 0);

		centerTextRela.addView(musicName, musicNameLayout);
		centerTextRela.addView(singerName, singerNameLayout);
		getMusicRela().addView(centerTextRela, centerTextRelaLayout);
	}
	
	@Override
	protected void createSecondlButton() {
		return ;
	}

}
