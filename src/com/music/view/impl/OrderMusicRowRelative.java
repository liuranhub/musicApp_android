package com.music.view.impl;

import com.music.musicapp.R;
import com.music.util.impl.MusicBase;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout.LayoutParams;

public class OrderMusicRowRelative extends MusicRowRealtiveBase {

	private String orderName = null;

	public OrderMusicRowRelative(Activity activity, Integer id, String orderName) {
		super(activity, id);
		this.orderName = orderName;
	}

	@Override
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
		 * centerTextRelaÎÄ×Ö
		 * */
		RelativeLayout centerTextRela = new RelativeLayout(getActivity()
				.getBaseContext());
		centerTextRela.setId(getId());
		centerTextRela.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				startMusicPlayerServiceIndex(getId());
			}
		});

		centerTextRela.setOnLongClickListener(new OnLongClickListener() {
			public boolean onLongClick(View v) {
				AlertDialog.Builder builder = new Builder(getActivity());
				builder.setItems(
						getActivity().getResources().getStringArray(
								R.array.order_music_contral),
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								if (which == 0) {
									MusicBase.createMusic().getOrderMap()
											.getOrder(orderName)
											.remove(getId());
									getActivity().sendBroadcast(new Intent().setAction("updata_order_music"));
								} else {
									Toast.makeText(
											getActivity().getBaseContext(),
											"ÐÞ¸Ä", Toast.LENGTH_LONG).show();
								}
							}
						});
				builder.show();

				return false;
			}
		});
		LayoutParams centerTextRelaLayout = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.FILL_PARENT);
		centerTextRelaLayout.setMargins(75, 0, 0, 0);

		centerTextRela.addView(musicName, musicNameLayout);
		centerTextRela.addView(singerName, singerNameLayout);
		getMusicRela().addView(centerTextRela, centerTextRelaLayout);
	}
	
	protected void createSecondlButton() {
		return ;
	}

}
