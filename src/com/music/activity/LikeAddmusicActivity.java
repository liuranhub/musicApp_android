package com.music.activity;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.music.musicapp.R;
import com.music.util.impl.MusicBase;
import com.music.util.impl.MusicInformation;
import com.music.util.inter.Music;
import com.music.view.impl.MusicRowRelativeHandLike;
import com.music.view.inter.MusicRowRelative;

public class LikeAddmusicActivity extends Activity {

	private Music music = MusicBase.createMusic();

	/**
	 * 滚动条中的LinearLayout保存所有的Music
	 * */
	private LinearLayout musicContext = null;

	/**
	 * 为like增加音乐的Activity的类,显示所有的歌曲,并提供操作选择
	 * */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.order_addmusic_activity);

		musicContext = (LinearLayout) findViewById(R.id.order_addmusic_context);

		/**
		 * 为了启动这个Activity更加快捷选择的是先加载前12个数据,等待显示在用户面前后再加载后面的数据
		 * */
		initOrderContext(0, 12);

		new Timer().schedule(new TimerTask() {
			public void run() {
				runOnUiThread(new Runnable() {
					public void run() {
						initOrderContext(12, music.getMusicList().size());
					}
				});
			}
		}, 1000);
	}

	/**
	 * @param start其实下标
	 * @param end结束下标
	 * */
	private void initOrderContext(int start, int end) {
		LinkedList<MusicInformation> list = music.getMusicList();

		for (int i = start; i < end; i++) {
			MusicRowRelative row = new MusicRowRelativeHandLike(this, list.get(
					i).getLocalId());
			musicContext.addView(row.getMusicView(), row.getMusicLayout());
		}
	}

	/**
	 * 单击完成按钮提交数据
	 * */
	public void addMusicSubmitOnClick(View view) {
		Toast.makeText(getBaseContext(), "标记完成", Toast.LENGTH_SHORT).show();

		Intent i1 = new Intent();
		i1.setAction("write_file_order");
		sendBroadcast(i1);

		Intent i2 = new Intent();
		i2.setAction("write_file_music");
		sendBroadcast(i2);

		finish();
	}

	/**
	 * 点击返回按钮提交数据
	 * */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Toast.makeText(getBaseContext(), "标记完成", Toast.LENGTH_SHORT).show();

			Intent i1 = new Intent();
			i1.setAction("write_file_order");
			sendBroadcast(i1);

			Intent i2 = new Intent();
			i2.setAction("write_file_music");
			sendBroadcast(i2);

			finish();
		}
		return false;
	}
}
