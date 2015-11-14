package com.music.view.impl;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

import com.music.activity.NetMusicActivity;
import com.music.bean.MusicTypeJson;
import com.music.musicapp.R;
import com.music.view.inter.NetViewRelative;

public class NetTableClassElement implements NetViewRelative {

	private int id;
	private String typeName;
	private RelativeLayout relativity;

	public NetTableClassElement(final Activity activity, MusicTypeJson type) {

		LayoutInflater flater = activity.getLayoutInflater();
		relativity = (RelativeLayout) flater.inflate(
				R.layout.net_classify_relative, null);

		TextView textView = (TextView) relativity.getChildAt(1);
		textView.setText(type.getTypeName());

		this.id = type.getId();
		this.typeName = type.getTypeName();

		relativity.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(activity, NetMusicActivity.class);
				String url = "typeAction!musics?typeId=" + id
						+ "&pageNo=0&pageSize=50";
				intent.putExtra("url", url);
				intent.putExtra("name", typeName);
				activity.startActivity(intent);
			}
		});
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public View getView() {
		return relativity;
	}

	public LayoutParams getLayout() {
		return null;
	}

	public int getId() {
		return id;
	}

}
