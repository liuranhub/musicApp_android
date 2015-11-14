package com.music.activity;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.music.adapter.PlayerViewPagerAdapter;
import com.music.bean.MusicClassJson;
import com.music.bean.MusicOrderJson;
import com.music.bean.MusicRowJson;
import com.music.bean.MusicTypeJson;
import com.music.json.MusicJsonUtil;
import com.music.musicapp.R;
import com.music.net.impl.ConnectObtainJson;
import com.music.util.impl.InitUtil;
import com.music.view.impl.MusicRowNetMusic;
import com.music.view.impl.MusicViewPager;
import com.music.view.impl.NetTableClassElement;
import com.music.view.impl.NetTableOrderElement;
import com.music.view.impl.NetTableRow;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TableLayout;

public class NetActivity extends Activity {

	private final static int SLEEPTIME = 500;

	private RelativeLayout viewpagerRela = null;

	private MusicViewPager viewPager = null;
	private View order, recommend, sort, classify;
	private LinkedList<View> options = new LinkedList<View>();
	private TableLayout orderTable = null;
	private TableLayout classTable = null;
	private Activity me = null;
	private LinearLayout searchLinear = null;
	private TextView searchWord = null;
	private LinearLayout top100Context = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.net_activity);
		initViewPager();
		me = this;
	}

	private void initViewPager() {
		viewpagerRela = (RelativeLayout) findViewById(R.id.net_viewPager_rela);

		viewPager = new MusicViewPager(getBaseContext());

		LayoutParams viewPagerLayout = new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		viewpagerRela.addView(viewPager, viewPagerLayout);

		ArrayList<View> viewPagerList = new ArrayList<View>();

		LayoutInflater flater = getLayoutInflater();

		order = flater.inflate(R.layout.net_viewpager_order, null);
		orderTable = (TableLayout) order.findViewById(R.id.net_order_table);

		recommend = flater.inflate(R.layout.net_viewpager_recommed, null);
		searchLinear = (LinearLayout) recommend
				.findViewById(R.id.searchmusic_context);
		searchWord = (TextView) recommend.findViewById(R.id.word_text);

		sort = flater.inflate(R.layout.net_viewpager_sort, null);

		classify = flater.inflate(R.layout.net_viewpager_classify, null);
		classTable = (TableLayout) classify
				.findViewById(R.id.net_classify_table);
		top100Context = (LinearLayout) sort.findViewById(R.id.sort_context);

		View order_t = findViewById(R.id.net_order);
		View recommend_t = findViewById(R.id.net_recommend);
		View sort_t = findViewById(R.id.net_sort);
		View classify_t = findViewById(R.id.net_classify);

		options.add(recommend_t);
		options.add(sort_t);
		options.add(order_t);
		options.add(classify_t);

		viewPagerList.add(recommend);
		viewPagerList.add(sort);
		viewPagerList.add(order);
		viewPagerList.add(classify);

		recommend_t.setOnClickListener(new OptionsOnClickListener(0));
		sort_t.setOnClickListener(new OptionsOnClickListener(1));
		order_t.setOnClickListener(new OptionsOnClickListener(2));
		classify_t.setOnClickListener(new OptionsOnClickListener(3));

		viewPager.setAdapter(new PlayerViewPagerAdapter(viewPagerList));

		viewPager.setCurrentItem(0);

	}

	class OptionsOnClickListener implements OnClickListener {

		private int id = 0;
		private boolean connnet = true;

		public OptionsOnClickListener(int id) {
			this.id = id;
		}

		public void onClick(View v) {
			viewPager.setCurrentItem(id);
			for (View view : options) {
				view.setBackgroundResource(R.drawable.list_option_backgroud_default);
			}
			options.get(id).setBackgroundResource(
					R.drawable.list_option_backgroud_selected);

			if (id == 2 && connnet) {

				new Timer().schedule(new TimerTask() {

					public void run() {
						runOnUiThread(new Runnable() {
							public void run() {
								connnet = false;
								obtainOrdersFormServer();
							}
						});
					}
				}, SLEEPTIME);

			}

			if (id == 3 && connnet) {
				connnet = false;
				new Timer().schedule(new TimerTask() {

					public void run() {
						runOnUiThread(new Runnable() {
							public void run() {
								connnet = false;
								obtainClassFormServer();
							}
						});
					}
				}, SLEEPTIME);
			}

			if (id == 1 && connnet) {
				connnet = false;

				new Timer().schedule(new TimerTask() {

					public void run() {
						runOnUiThread(new Runnable() {
							public void run() {
								connnet = false;
								obtainTop100();
							}
						});
					}
				}, SLEEPTIME);
			}

		}
	}

	public void submitOnClick(View view) {
		searchLinear.removeAllViews();
		String word = searchWord.getText().toString().trim();
		ConnectObtainJson conn = new ConnectObtainJson();

		try {
			word = new String(word.getBytes(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		System.out.println(word);

		String json = conn.getResult(InitUtil.getServerurl()
				+ "musicAction!findMusic?word=" + word);
		List<MusicRowJson> list = MusicJsonUtil.createMusicList(json);
		System.out.println(json);
		int index = 0;
		for (MusicRowJson rowjson : list) {
			index++;
			if (index > 50) {
				break;
			}
			MusicRowNetMusic row = new MusicRowNetMusic(rowjson, this, index);
			searchLinear.addView(row.getMusicView(), row.getMusicLayout());
		}
	}

	private void obtainTop100() {
		ConnectObtainJson conn = new ConnectObtainJson();
		String json = conn
				.getResult(InitUtil.getServerurl() + "sortAction!top");
		List<MusicRowJson> list = MusicJsonUtil.createMusicList(json);

		int index = 0;
		for (MusicRowJson rowjson : list) {
			index++;
			MusicRowNetMusic row = new MusicRowNetMusic(rowjson, this, index);
			top100Context.addView(row.getMusicView(), row.getMusicLayout());
		}
	}

	private void obtainClassFormServer() {
		ConnectObtainJson conn = new ConnectObtainJson();
		String json = conn.getResult(InitUtil.getServerurl()
				+ "typeAction!types");
		MusicClassJson classJson = MusicJsonUtil.createTypeClass(json);

		for (String name : classJson.getMap().keySet()) {
			TextView textName = new TextView(this);
			textName.setText("  " + name);
			textName.setTextSize(16);
			textName.setTextColor(Color.WHITE);
			classTable.addView(textName);
			int i = 0;
			NetTableRow row = new NetTableRow(this);
			for (MusicTypeJson type : classJson.getMap().get(name)) {
				i++;
				NetTableClassElement element = new NetTableClassElement(this,
						type);
				row.addViews(element.getView());
				if (i % 4 == 0 || i == classJson.getMap().get(name).size()) {
					classTable.addView(row);
					row = new NetTableRow(this);
				}
			}

		}

	}

	private void obtainOrdersFormServer() {
		ConnectObtainJson conn = new ConnectObtainJson();
		String json = conn.getResult(InitUtil.getServerurl()
				+ "orderAction!orders.action");

		List<MusicOrderJson> orders = MusicJsonUtil.createOrderList(json);
		NetTableRow row = new NetTableRow(this);
		for (int i = 0; i < orders.size(); i++) {
			MusicOrderJson order = orders.get(i);
			row.addView(new NetTableOrderElement(me, order).getView());

			if ((i + 1) % 3 == 0 || i == orders.size()) {
				orderTable.addView(row);
				row = new NetTableRow(me);
			}
		}
	}

}
