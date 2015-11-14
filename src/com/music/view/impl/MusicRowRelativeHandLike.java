package com.music.view.impl;

import com.music.musicapp.R;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;

public class MusicRowRelativeHandLike extends MusicRowRelativeAbstract {

	private ImageView addImage = null;

	public MusicRowRelativeHandLike(Activity activity, Integer id) {
		setActivity(activity);
		setId(id);
	}

	protected void create() {
		createFirstButton();
		createText();
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
		 * centerTextRelaÎÄ×Ö
		 * */
		RelativeLayout centerTextRela = new RelativeLayout(getActivity()
				.getBaseContext());
		centerTextRela.setId(getId());
		centerTextRela.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (getMusicInf().isLike()) {
					getMusicInf().setLike(false);
				} else {
					getMusicInf().setLike(true);
				}
				resetFirstButtonImage();
			}
		});

		centerTextRela.setOnLongClickListener(new OnLongClickListener() {
			public boolean onLongClick(View v) {
				Toast.makeText(getActivity().getBaseContext(), "long clicked",
						Toast.LENGTH_SHORT).show();
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

	protected void createFirstButton() {
		/**
		 * addImage
		 * */
		addImage = new ImageView(getActivity().getBaseContext());

		resetFirstButtonImage();

		LayoutParams addImageLayout = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		addImageLayout.addRule(RelativeLayout.CENTER_VERTICAL);
		addImageLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		addImageLayout.setMargins(20, 0, 20, 0);
		addImageLayout.width = 30;
		addImageLayout.height = 30;
		getMusicRela().addView(addImage, addImageLayout);
	}

	private void resetFirstButtonImage() {
		if (getMusicInf().isLike()) {
			addImage.setBackgroundResource(R.drawable.bf_player_like_red);
		} else {
			addImage.setBackgroundResource(R.drawable.bf_player_like);
		}
	}

	public void setOrderPopupWindow(OrderPopupWindow orderPopupWindow) {
		return;
	}

}
