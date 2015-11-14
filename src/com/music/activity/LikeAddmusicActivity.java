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
	 * �������е�LinearLayout�������е�Music
	 * */
	private LinearLayout musicContext = null;

	/**
	 * Ϊlike�������ֵ�Activity����,��ʾ���еĸ���,���ṩ����ѡ��
	 * */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.order_addmusic_activity);

		musicContext = (LinearLayout) findViewById(R.id.order_addmusic_context);

		/**
		 * Ϊ���������Activity���ӿ��ѡ������ȼ���ǰ12������,�ȴ���ʾ���û���ǰ���ټ��غ��������
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
	 * @param start��ʵ�±�
	 * @param end�����±�
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
	 * ������ɰ�ť�ύ����
	 * */
	public void addMusicSubmitOnClick(View view) {
		Toast.makeText(getBaseContext(), "������", Toast.LENGTH_SHORT).show();

		Intent i1 = new Intent();
		i1.setAction("write_file_order");
		sendBroadcast(i1);

		Intent i2 = new Intent();
		i2.setAction("write_file_music");
		sendBroadcast(i2);

		finish();
	}

	/**
	 * ������ذ�ť�ύ����
	 * */
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Toast.makeText(getBaseContext(), "������", Toast.LENGTH_SHORT).show();

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
