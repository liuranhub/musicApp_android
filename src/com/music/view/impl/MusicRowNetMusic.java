package com.music.view.impl;

import com.music.bean.MusicRowJson;
import com.music.musicapp.R;
import com.music.service.DownloadMusicService;
import com.music.util.impl.InitUtil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class MusicRowNetMusic {
	private MusicRowJson row = null;
	private RelativeLayout relative = null;
	private LayoutParams layout = null;
	private Activity activity = null;
	private Integer index;

	public MusicRowNetMusic(MusicRowJson row, Activity activity, int index) {
		this.row = row;
		this.activity = activity;
		this.index = index;
	}

	public Activity getActivity() {
		return activity;
	}

	private void init() {
		relative = new RelativeLayout(activity);
		layout = new LayoutParams(LayoutParams.FILL_PARENT, 80);
		relative.setBackgroundResource(R.drawable.net_context_background_text);
		create();

	}

	/**
	 * 创建序号
	 * */
	private void createNumber() {
		TextView number = new TextView(getActivity().getBaseContext());
		String text = "" + index;
		number.setText(text);
		number.setTextSize(19);
		number.setTextColor(Color.BLACK);
		number.setSingleLine();
		LayoutParams numberLayout = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		numberLayout.setMargins(20, 0, 0, 0);
		numberLayout.addRule(RelativeLayout.CENTER_VERTICAL);

		relative.addView(number, numberLayout);
	}

	/**
	 * 创建歌曲名
	 * */
	private void createText() {
		TextView musicName = new TextView(getActivity().getBaseContext());
		musicName.setText(row.getMusicName());
		musicName.setTextSize(19);
		musicName.setTextColor(Color.BLACK);
		musicName.setSingleLine();
		musicName.setWidth(480);
		LayoutParams musicNameLayout = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		musicNameLayout.setMargins(80, 0, 0, 0);
		musicNameLayout.addRule(RelativeLayout.CENTER_VERTICAL);

		relative.addView(musicName, musicNameLayout);
	}

	/**
	 * 创建下载按钮
	 * */
	private void createButton() {
		ImageView contralImage = new ImageView(getActivity().getBaseContext());

		contralImage.setBackgroundResource(R.drawable.net_download_x);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		LayoutParams contralImageLayout = new LayoutParams(60, 60);
		contralImageLayout.addRule(RelativeLayout.CENTER_VERTICAL);
		contralImageLayout.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		contralImageLayout.setMargins(10, 0, 20, 0);
		contralImage.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(activity.getBaseContext(),
						DownloadMusicService.class);
				intent.putExtra("urlStr", InitUtil.getServerurl()
						+ "downLoadMusicAction?music_id=" + row.getId());

				intent.putExtra("musicName", row.getMusicName());
				System.out.println("开始下载");
				activity.startService(intent);
			}
		});
		relative.addView(contralImage, contralImageLayout);
	}

	private void create() {
		createText();
		createButton();
		createNumber();
	}

	public View getMusicView() {
		if (relative == null) {
			init();
		}
		return relative;
	}

	public LayoutParams getMusicLayout() {
		if (layout == null) {
			init();
		}
		return layout;
	}

}
