package com.music.view.impl;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

import com.music.activity.LocalMusicActivity;
import com.music.activity_util.impl.ContralMusic;
import com.music.musicapp.R;
import com.music.service.UpdateOrderService;
import com.music.util.impl.MusicBase;
import com.music.util.inter.Music;

public class MusicRowRealtiveBase extends MusicRowRelativeAbstract {

	/**
	 * 控制音乐的播放
	 * */
	private ContralMusic contralMusic = null;
	
	private Music music = MusicBase.createMusic();
	
	/**
	 * 队列窗口的引用
	 * */
	private OrderPopupWindow orderPopupWindow = null;

	public MusicRowRealtiveBase(Activity activity, Integer id) {
		setActivity(activity);
		setId(id);
		contralMusic = new ContralMusic(activity);
	}

	protected void create() {
		createFirstButton();
		createText();
		createSecondlButton();
	}

	private boolean show = true;

	protected void createSecondlButton() {
		ImageView contralImage = new ImageView(getActivity().getBaseContext());

		contralImage.setBackgroundResource(R.drawable.list_context_show_x);
		contralImage.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				LocalMusicActivity localActivity = (LocalMusicActivity) getActivity();
				if (localActivity.getShowId() == getId().intValue()) {
					show = false;
				} else {
					show = true;
				}
				if (show) {
					Intent intent = new Intent();
					intent.setAction("add_d");
					intent.putExtra("id", getId());
					getActivity().sendBroadcast(intent);
				} else {
					Intent intent = new Intent();
					intent.setAction("remove_d");
					intent.putExtra("id", getId());
					getActivity().sendBroadcast(intent);
				}
			}
		});

		LayoutParams contralImageLayout = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		contralImageLayout.addRule(RelativeLayout.CENTER_IN_PARENT);
		contralImageLayout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		contralImageLayout.setMargins(10, 0, 30, 0);

		getMusicRela().addView(contralImage, contralImageLayout);
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

		centerTextRela.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				return false;
			}

		});

		centerTextRela.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				startMusicPlayerServiceIndex(getId());
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
		ImageView addImage = new ImageView(getActivity().getBaseContext());
		addImage.setBackgroundResource(R.drawable.list_context_add_x);
		addImage.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				music.addOrderListElement(getMusicInf().getLocalId());

				getActivity().startService(
						new Intent(getActivity().getBaseContext(),
								UpdateOrderService.class));
				if (orderPopupWindow != null) {
					orderPopupWindow.addPopupWindowElement(getMusicInf()
							.getLocalId());
				}
			}
		});
		LayoutParams addImageLayout = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		addImageLayout.addRule(RelativeLayout.CENTER_VERTICAL);
		addImageLayout.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		addImageLayout.setMargins(20, 0, 20, 0);
		addImageLayout.width = 30;
		addImageLayout.height = 30;
		getMusicRela().addView(addImage, addImageLayout);
	}

	protected void startMusicPlayerServiceIndex(int index) {
		contralMusic.startMusicPlayerServiceIndex(index);
	}

	public void setOrderPopupWindow(OrderPopupWindow orderPopupWindow) {
		this.orderPopupWindow = orderPopupWindow;
		initMusic();
	}

}
