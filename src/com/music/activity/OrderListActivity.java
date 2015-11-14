package com.music.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.music.musicapp.R;
import com.music.util.impl.MusicBase;
import com.music.util.inter.Music;
import com.music.view.impl.OrderListRowRelative;

public class OrderListActivity extends Activity {

	private LinearLayout orderContext = null;

	private Music music = MusicBase.createMusic();

	private RecreateBroadcastReceiver recreate = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.order_list_activity);
		initOrderContext();
		initBroadCast();
	}

	protected void onDestroy() {
		super.onDestroy();
		if (recreate != null) {
			unregisterReceiver(recreate);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		initOrderContext();
	}

	private void initOrderContext() {
		orderContext = (LinearLayout) findViewById(R.id.order_list_context);
		orderContext.removeAllViews();
		for (String orderName : music.getOrderMap().getAllOrder()) {
			OrderListRowRelative row = new OrderListRowRelative(this, orderName);
			orderContext.addView(row.getMusicView(), row.getMusicLayout());
		}
	}

	private void initBroadCast() {
		IntentFilter filterUpdate = new IntentFilter();
		filterUpdate.addAction("updata_order_list");

		recreate = new RecreateBroadcastReceiver();
		registerReceiver(recreate, filterUpdate);

	}

	class RecreateBroadcastReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			initOrderContext();
		}
	}

	public void orderListAddOnClick(View view) {
		Intent intent = new Intent(getBaseContext(), OrderCreateActivity.class);
		startActivity(intent);
	}
	
	public void orderListDeleteOnClick(View view){
		Intent intent = new Intent(getApplicationContext(), OrderListDeleteActivity.class) ;
		startActivity(intent);
	}
}
