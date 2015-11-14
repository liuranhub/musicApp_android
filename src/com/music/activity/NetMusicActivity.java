package com.music.activity;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.music.bean.MusicRowJson;
import com.music.json.MusicJsonUtil;
import com.music.musicapp.R;
import com.music.net.impl.ConnectObtainJson;
import com.music.util.impl.InitUtil;
import com.music.view.impl.MusicRowNetMusic;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NetMusicActivity extends Activity {

	private LinearLayout context = null;

	private List<MusicRowJson> musicrows = new LinkedList<MusicRowJson>();

	private Integer index = 0;
	private BroadcastReceiver download = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.netmusic_activity);

		String name = getIntent().getStringExtra("name");
		TextView textName = (TextView) findViewById(R.id.netmusic_toptext_t);
		if (name != null) {
			textName.setText(name);
		}

		new Timer().schedule(new TimerTask() {

			public void run() {

				ConnectObtainJson conn = new ConnectObtainJson();
				String url = getIntent().getStringExtra("url").trim();
				System.out.println(url);
				String jsonStr = conn.getResult(InitUtil.getServerurl() + url);

				musicrows = MusicJsonUtil.createMusicList(jsonStr);

				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						initContext();
					}
				});
			}
		}, 100);

		/**
		 * 广播的注册
		 * */
		IntentFilter filterDown = new IntentFilter();
		filterDown.addAction("downloaded");

		// download = new DownLoadFinishBroadCastReceiver(this);

		download = new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {

				Toast.makeText(getBaseContext(), "下载完成", Toast.LENGTH_LONG)
						.show();
			}
		};
		registerReceiver(download, filterDown);
	}

	@Override
	protected void onDestroy() {
		if (download != null) {
			unregisterReceiver(download);
		}
		super.onDestroy();
	}

	private void initContext() {
		context = (LinearLayout) findViewById(R.id.netmusic_context);

		for (MusicRowJson rowjson : musicrows) {
			index = index + 1;
			MusicRowNetMusic row = new MusicRowNetMusic(rowjson, this, index);
			context.addView(row.getMusicView(), row.getMusicLayout());
		}

	}

	public void backOnClick(View view) {
		finish();
	}
}
