package com.music.activity;

import java.util.LinkedList;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.music.musicapp.R;
import com.music.util.impl.MusicBase;
import com.music.util.inter.Music;

public class OrderCreateActivity extends Activity {

	private Music music = MusicBase.createMusic();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.order_create_activity);
	}

	public void createOnClick(View view) {

		EditText editText = (EditText) findViewById(R.id.ordername);

		String orderName = editText.getText().toString().trim();

		if (orderName != null) {
			boolean success = music.getOrderMap().addOrder(orderName);

			if (success) {
				Toast.makeText(getBaseContext(), "创建成功", Toast.LENGTH_LONG)
						.show();
				;
				Intent intent = new Intent(getBaseContext(),
						OrderListActivity.class);
				startActivity(intent);
				finish();
			}
		}

		for (String str : music.getOrderMap().getAllOrder()) {
			System.out.println(str);
		}

		Toast.makeText(getBaseContext(), "创建失败", Toast.LENGTH_LONG).show();

		Map<String, LinkedList<Integer>> map = music.getOrderMap()
				.getMusicOrderMap();

		JSONObject json = new JSONObject();
		try {
			json.put("map", map);

			System.out.println(json.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

}
