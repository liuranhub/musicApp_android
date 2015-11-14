package com.music.view.impl;

import com.music.activity.NetMusicActivity;
import com.music.bean.MusicOrderJson;
import com.music.musicapp.R;
import com.music.view.inter.NetViewRelative;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class NetTableOrderElement implements NetViewRelative {

	private RelativeLayout relative = null;
	private int id;
	private String orderName = null;

	public NetTableOrderElement(final Activity activity, MusicOrderJson order) {
		LayoutInflater flater = activity.getLayoutInflater();
		relative = (RelativeLayout) flater.inflate(R.layout.net_order_relative,
				null);
		setId(order.getId());

		TextView textView = (TextView) relative.getChildAt(1);
		textView.setText(order.getOrderName());
		this.orderName = order.getOrderName();

		relative.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(activity, NetMusicActivity.class);
				String url = "orderAction!orderMusic?orderId=" + id;

				intent.putExtra("url", url);
				intent.putExtra("name", orderName);
				activity.startActivity(intent);
			}
		});
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public View getView() {
		return relative;
	}

	public LayoutParams getLayout() {
		return null;
	}
}
