package com.music.view.impl;

import com.music.activity_util.impl.ContralMusic;
import com.music.activity_util.inter.ContralMusicIf;
import com.music.musicapp.R;
import com.music.util.impl.MusicBase;
import com.music.util.impl.MusicInformation;
import com.music.util.inter.Music;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class OrderPopupWindow extends ModePopupWindow {

	private LinearLayout musicOrder = null;
	private Activity activity = null;
	private Music music = MusicBase.createMusic();

	/**
	 * layout中必须要有一个LinearLayout
	 * */
	public OrderPopupWindow(Activity activity, int layout) {
		super(activity, layout);
		this.activity = activity;
		musicOrder = (LinearLayout) getView().findViewById(R.id.musicOrder);

		for (Integer id : music.getOrderList()) {
			addPopupWindowElement(id);
		}
	}

	public void addElement(View view) {
		this.addViewListElement(view);
		musicOrder.addView(view);
	}

	public void removeElement(View view) {
		musicOrder.removeView(view);
	}

	@Override
	public void changeColor(int id) {

	}

	public void addPopupWindowElement(final Integer id) {
		activity.runOnUiThread(new Runnable() {
			public void run() {
				final MusicBase music = MusicBase.createMusic();
				final RelativeLayout thisRelative = new RelativeLayout(activity);
				thisRelative.setPadding(10, 5, 5, 10);
				thisRelative
						.setBackgroundResource(R.drawable.bf_player_pager_first_text_bg);

				ImageView firstImage = new ImageView(activity);
				firstImage
						.setImageResource(R.drawable.bf_player_activity_order_delete);
				LayoutParams firstImageLayout = new LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				firstImageLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
				firstImageLayout.addRule(RelativeLayout.CENTER_VERTICAL);

				final ImageView removeImage = new ImageView(activity);
				removeImage
						.setImageResource(R.drawable.bf_player_activity_order_delete);
				removeImage.setId(music.getOrderList().size() - 1);
				LayoutParams removeImageLayout = new LayoutParams(
						LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				removeImage.setPadding(5, 0, 0, 10);

				removeImage.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						activity.runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								music.removeOrderListElement(removeImage.getId());
								removeElement(thisRelative);
							}
						});
						System.out.println(music.getOrderList().size());
					}
				});
				removeImageLayout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
				removeImageLayout.addRule(RelativeLayout.CENTER_VERTICAL);

				final TextView text = new TextView(activity);

				MusicInformation inf = MusicBase.createMusic().getMusicList()
						.get(id - 1);

				text.setText(inf.getMusicName());
				text.setId(id);
				text.setTextSize(20);
				text.setSingleLine(true);
				text.setTextColor(Color.argb(240, 245, 245, 245));
				text.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						ContralMusicIf contral = new ContralMusic(activity);
						contral.startMusicPlayerServiceIndex(text.getId());
						dismiss();
					}
				});
				RelativeLayout.LayoutParams textLayout = new RelativeLayout.LayoutParams(
						LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
				textLayout.leftMargin = 60;
				textLayout.rightMargin = 48;
				textLayout.addRule(RelativeLayout.CENTER_VERTICAL);

				thisRelative.addView(firstImage, firstImageLayout);
				thisRelative.addView(text, textLayout);
				thisRelative.addView(removeImage, removeImageLayout);

				addElement(thisRelative);
			}
		});

	}

}
