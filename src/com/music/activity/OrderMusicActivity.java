package com.music.activity;

import java.util.LinkedList;

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

import com.music.musicapp.R;
import com.music.util.impl.MusicBase;
import com.music.util.inter.Music;
import com.music.view.impl.OrderMusicRowRelative;
import com.music.view.inter.MusicRowRelative;

public class OrderMusicActivity extends Activity {

	private TextView orderName = null;

	private String orderNameStr = null;

	private Music music = MusicBase.createMusic();

	private LinearLayout orderContext = null;

	private RecreateBroadcastReceiver recreate = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.order_music_activity);
		orderName = (TextView) findViewById(R.id.order_music_name);
		orderNameStr = getIntent().getStringExtra("ordername");
		orderName.setText(orderNameStr);
		initBroadCast();
	}

	@Override
	protected void onResume() {
		initOrderContext();
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (recreate != null) {
			unregisterReceiver(recreate);
		}
	}

	private void initOrderContext() {
		orderContext = (LinearLayout) findViewById(R.id.order_list_context);
		orderContext.removeAllViews();
		LinkedList<Integer> list = music.getOrderMap().getOrder(orderNameStr);
		for (Integer id : list) {
			MusicRowRelative row = new OrderMusicRowRelative(this, id,
					orderNameStr);

			orderContext.addView(row.getMusicView(), row.getMusicLayout());
		}
	}

	public void addMusicToOrderOnClick(View view) {
		Intent intent = new Intent(getBaseContext(),
				OrderAddmusicActivity.class);
		intent.putExtra("ordername", orderNameStr);
		startActivity(intent);
	}

	private void initBroadCast() {
		IntentFilter filterUpdate = new IntentFilter();
		filterUpdate.addAction("updata_order_music");

		recreate = new RecreateBroadcastReceiver();
		registerReceiver(recreate, filterUpdate);

	}

	class RecreateBroadcastReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			initOrderContext();
		}

	}
}
