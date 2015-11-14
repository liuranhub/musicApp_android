package com.music.activity;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.music.musicapp.R;
import com.music.sort.SortMusic;
import com.music.util.impl.MusicBase;
import com.music.util.impl.MusicInformation;
import com.music.util.inter.Music;
import com.music.view.impl.MusicRowSystem;
import com.music.view.inter.MusicRowRelative;

public class SystemOrderActivity extends Activity {

	private LinearLayout linear = null;

	private Music music = MusicBase.createMusic();

	private String type = null;
	private TextView textName = null;

	SortMusic sort = new SortMusic();
	Object[] musics = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		type = getIntent().getStringExtra("type");

		requestWindowFeature(Window.FEATURE_NO_TITLE);

		if ("like".equals(type)) {
			setContentView(R.layout.order_like_activity);
		} else {
			setContentView(R.layout.order_other_activity);
		}

		linear = (LinearLayout) findViewById(R.id.order_list_context);

		textName = (TextView) findViewById(R.id.name);

		initPopuWindow();

	}

	@Override
	protected void onResume() {
		super.onResume();

		linear.removeAllViews();

		if ("like".equals(type)) {

			textName.setText("我喜欢");

			initLikeList();
		} else if ("recent".equals(type)) {

			textName.setText("最近播放");

			initRecetnList(0, 15);

			new Timer().schedule(new TimerTask() {

				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						public void run() {
							initRecetnList(15, 50);
						}
					});
				}
			}, 1500);

		} else if ("download".equals(type)) {
			textName.setText("我喜欢");

			initDownload();

		} else if ("count".equals(type)) {

			textName.setText("播放最多");

			initCountList(0, 15);

			new Timer().schedule(new TimerTask() {

				public void run() {
					runOnUiThread(new Runnable() {
						public void run() {
							initCountList(15, 50);
						}
					});
				}
			}, 1500);

		}

	}

	private void initDownload() {
		Music music = MusicBase.createMusic();

		for (MusicInformation inf : music.getMusicList()) {
			if (inf.isDownload()) {
				init(inf);
			}
		}
	}

	private void initLikeList() {

		LinkedList<MusicInformation> list = music.getMusicList();

		for (MusicInformation inf : list) {
			if (inf.isLike()) {
				init(inf);
			}
		}

	}

	private void initRecetnList(int start, int end) {

		musics = sort.sortByPlayTime();

		for (int i = start; i < end; i++) {
			MusicInformation inf = (MusicInformation) musics[i];
			if (inf.getMusicTime() != null) {
				init(inf);
			}
		}
	}

	private void initCountList(int start, int end) {

		musics = sort.sortByPlayCount();

		for (int i = start; i < end; i++) {
			MusicInformation inf = (MusicInformation) musics[i];
			if (inf.getPalyCount() > 0) {
				init(inf);
			}
		}

	}

	private void init(MusicInformation inf) {

		MusicRowRelative row = new MusicRowSystem(this, inf.getLocalId());
		linear.addView(row.getMusicView(), row.getMusicLayout());
	}

	private void initPopuWindow() {

	}

	public void likeAddOnClick(View view) {
		Intent intent = new Intent(getBaseContext(), LikeAddmusicActivity.class);
		startActivity(intent);
	}

}
