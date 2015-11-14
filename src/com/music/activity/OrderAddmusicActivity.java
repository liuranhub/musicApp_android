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
import android.widget.Toast;

import com.music.musicapp.R;
import com.music.util.impl.MusicBase;
import com.music.util.impl.MusicInformation;
import com.music.util.inter.Music;
import com.music.view.impl.MusicRowRelativeCheckBox;

public class OrderAddmusicActivity extends Activity {

	private Music music = MusicBase.createMusic();

	private LinearLayout musicContext = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.order_addmusic_activity);

		musicContext = (LinearLayout) findViewById(R.id.order_addmusic_context);
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

	private void initOrderContext(int start, int end) {
		LinkedList<MusicInformation> list = music.getMusicList();

		for (int i = start; i < end; i++) {
			MusicRowRelativeCheckBox row = new MusicRowRelativeCheckBox(this,
					list.get(i).getLocalId(), getIntent().getStringExtra(
							"ordername"));
			musicContext.addView(row.getMusicView(), row.getMusicLayout());
		}
	}

	public void addMusicSubmitOnClick(View view) {
		Toast.makeText(getBaseContext(), "Ìí¼ÓÍê³É", Toast.LENGTH_SHORT).show();

		Intent i1 = new Intent();
		i1.setAction("write_file_order");
		sendBroadcast(i1);

		Intent i2 = new Intent();
		i2.setAction("write_file_music");
		sendBroadcast(i2);

		finish();
	}

}
