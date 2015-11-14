package com.music.activity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import com.music.activity_util.impl.ContralMusic;
import com.music.activity_util.inter.ContralMusicIf;
import com.music.adapter.PlayerViewPagerAdapter;
import com.music.enums.PlayerEnum;
import com.music.musicapp.R;
import com.music.service.AutoPlayService;
import com.music.service.WriteFileService_Music;
import com.music.service.WriteFileService_Order;
import com.music.util.impl.MusicBase;
import com.music.util.impl.MusicInstrumentStatus;
import com.music.util.inter.Music;
import com.music.view.fartory.OrderPopupWindowFactory;
import com.music.view.impl.OrderPopupWindow;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends Activity {

	private static final int SLEEPTIME = 150;
	private ViewPager viewPager = null;

	private TextView musicNameView = null;

	private View pagerMy;
	private ImageView pause = null;

	private LinkedList<TextView> textViewList = new LinkedList<TextView>();

	private MusicInstrumentStatus musicStatus = MusicInstrumentStatus
			.getInstrumentStatus();

	private UpdateBroadcastReceiver receiverUpdate = null;

	private UpdateOrderPopuwindBroadcast orderPopuBroadcast = null;

	private WriteFileOrderBroadcastReceiver writeFileOrder = null;

	private WriteFileMusicBroadcastReceiver writeFileMusic = null;

	private downloadFinishBroadcastReceiver downloadFinish = null;

	private ContralMusicIf contralMusic = null;

	private Music music = MusicBase.createMusic();

	private OrderPopupWindow orderPopupWindow = null;

	private LinearLayout orderContext = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.menu_activity);
		contralMusic = new ContralMusic(this);
		initViewPager();
		initImageView();
		initBroadCast();
		initOrderPopWindow();

		initAllMusicCount();

		musicNameView = (TextView) findViewById(R.id.order_list_bottom_musicName);

		startService(new Intent(getBaseContext(), AutoPlayService.class));
		new Timer().schedule(new TimerTask() {

			public void run() {
				// startService(new Intent(getBaseContext(),
				// AutoLoginService.class));
			}
		}, 2000);

	}

	protected void onPause() {

		super.onPause();

		startService(new Intent(getBaseContext(), WriteFileService_Music.class));

		startService(new Intent(getBaseContext(), WriteFileService_Order.class));
	}

	private void initAllMusicCount() {
		TextView allMusicCount = (TextView) pagerMy
				.findViewById(R.id.allMusic_count);
		StringBuffer text = new StringBuffer();
		text.append(music.getMusicList().size()).append("首");
		allMusicCount.setText(text);
	}

	private void initOrderPopWindow() {
		orderPopupWindow = new OrderPopupWindowFactory().create(this);
	}

	private void initImageView() {
		music.getCurrentMusicInfor();
		pause = (ImageView) findViewById(R.id.order_list_bottom_pause);
	}

	private void initViewPager() {

		TextView my = (TextView) findViewById(R.id.menu_text_my);
		my.setOnClickListener(new TextOnClickListener(0));

		TextView net = (TextView) findViewById(R.id.menu_text_net);
		net.setOnClickListener(new TextOnClickListener(1));

		textViewList.add(my);
		textViewList.add(net);

		viewPager = (ViewPager) findViewById(R.id.menu_viewPager);

		ArrayList<View> viewPagerList = new ArrayList<View>();
		LayoutInflater flater = getLayoutInflater();

		pagerMy = flater.inflate(R.layout.menu_viewpager_my, null);

		// pagerNet = flater.inflate(R.layout.menu_viewpager_net, null);

		viewPagerList.add(pagerMy);
		// viewPagerList.add(pagerNet);

		viewPager.setAdapter(new PlayerViewPagerAdapter(viewPagerList));

		viewPager.setCurrentItem(0);
	}

	private void initMyOrderScrllo() {
		orderContext = (LinearLayout) pagerMy
				.findViewById(R.id.netmusic_context);
		orderContext.removeAllViews();
		for (String orderName : music.getOrderMap().getAllOrder()) {
			orderContext.addView(scrllo(orderName));
		}

		RelativeLayout orderReal = new RelativeLayout(getBaseContext());
		orderReal.setPadding(10, 10, 10, 0);
		LayoutParams orderRealLayout = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

		orderRealLayout.addRule(RelativeLayout.CENTER_IN_PARENT);

		ImageView image = new ImageView(getBaseContext());

		image.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(),
						OrderCreateActivity.class);
				startActivity(intent);
			}
		});

		image.setBackgroundResource(R.drawable.order_list_add_x);
		LayoutParams imageLayout = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		imageLayout.width = 130;
		imageLayout.height = 130;
		imageLayout.setMargins(0, 55, 0, 0);
		imageLayout.addRule(RelativeLayout.CENTER_IN_PARENT);
		orderReal.addView(image, imageLayout);

		orderContext.addView(orderReal);

	}

	private RelativeLayout scrllo(final String orderName) {
		RelativeLayout orderReal = new RelativeLayout(getBaseContext());
		orderReal.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(),
						OrderMusicActivity.class);
				intent.putExtra("ordername", orderName);
				startActivity(intent);
			}
		});

		orderReal.setPadding(20, 10, 20, 0);

		ImageView image = new ImageView(getBaseContext());
		image.setBackgroundResource(R.drawable.kg_ic_playing_bar_image_default);
		LayoutParams imageLayout = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		imageLayout.width = 130;
		imageLayout.height = 130;
		imageLayout.addRule(RelativeLayout.CENTER_IN_PARENT);

		TextView name = new TextView(getBaseContext());
		name.setText(orderName);
		name.setTextColor(Color.BLACK);
		LayoutParams nameLayout = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		nameLayout.addRule(RelativeLayout.CENTER_HORIZONTAL);
		name.setPadding(0, 155, 0, 0);

		orderReal.addView(image, imageLayout);
		orderReal.addView(name, nameLayout);

		return orderReal;

	}

	class TextOnClickListener implements OnClickListener {

		int id = 0;

		public TextOnClickListener(int id) {
			this.id = id;
		}

		public void onClick(View v) {
			viewPager.setCurrentItem(id);
			resetPagerTextView();
			((TextView) v).setTextColor(Color.WHITE);
		}

	}

	private void resetPagerTextView() {
		for (TextView view : textViewList) {
			view.setTextColor(Color.rgb(56, 56, 56));
		}
	}

	/**
	 * 启动后台服务（常规切换歌曲）
	 * */
	private void startMusicPlayerService(int type) {
		contralMusic.startMusicPlayerService(type);
	}

	protected void onResume() {
		super.onResume();
		updatePauseImage();
		initMyOrderScrllo();
		initAllMusicCount();
		updateMusicName();
	}

	protected void onDestroy() {
		super.onDestroy();
		if (receiverUpdate != null) {
			unregisterReceiver(receiverUpdate);
		}
		if (orderPopuBroadcast != null) {
			unregisterReceiver(orderPopuBroadcast);
		}
		if (writeFileOrder != null) {
			unregisterReceiver(writeFileOrder);
		}
		if (writeFileMusic != null) {
			unregisterReceiver(writeFileMusic);
		}
		if (downloadFinish != null) {
			unregisterReceiver(downloadFinish);
		}
	}

	public void pauseOnClick(View view) {
		try {
			Thread.sleep(SLEEPTIME);
			if (musicStatus.isPlaying()) {
				updataMusicStatus(false);
				startMusicPlayerService(PlayerEnum.STOP.ordinal());

			} else {
				updataMusicStatus(true);
				startMusicPlayerService(PlayerEnum.START.ordinal());
			}

			updatePauseImage();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void nextOnClick(View view) {
		try {
			Thread.sleep(SLEEPTIME);
			startMusicPlayerService(PlayerEnum.NEXT.ordinal());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试代码
	 * */
	public void playOrNextOnClick(View view) {

	}

	public void orderOnClick(View view) {
		orderPopupWindow.showAsDropDown(view);
		orderPopupWindow.updateStartPoint(view, 0, 30);
		orderPopupWindow.autoClosePopupWindow(15000);
	}

	public void userLoginOnClick(View view) {

		try {
			Thread.sleep(SLEEPTIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Intent intent = new Intent(this, UserLoginActivity.class);
		startActivity(intent);

	}

	public void gotoPlayerActivityOnClick(View view) {
		Intent intent = new Intent(this, PlayerActivity.class);
		startActivity(intent);
	}

	public void localMusicOnClick(View view) {
		Intent intent = new Intent(getBaseContext(), LocalMusicActivity.class);
		startActivity(intent);
	}

	public void likeOnClick(View view) {
		Intent intent = new Intent(getBaseContext(), SystemOrderActivity.class);
		intent.putExtra("type", "like");
		startActivity(intent);
	}

	public void recentOnClick(View view) {
		Intent intent = new Intent(getBaseContext(), SystemOrderActivity.class);
		intent.putExtra("type", "recent");
		startActivity(intent);
	}

	public void playCountOnClick(View view) {
		Intent intent = new Intent(getBaseContext(), SystemOrderActivity.class);
		intent.putExtra("type", "count");
		startActivity(intent);
	}

	public void createOrderOnClick(View view) {
		Intent intent = new Intent(getBaseContext(), OrderCreateActivity.class);
		startActivity(intent);
	}

	public void musicOrderTextOnClick(View view) {
		Intent intent = new Intent(getBaseContext(), OrderListActivity.class);
		startActivity(intent);
	}

	public void downloadedOnClick(View view) {
		Intent intent = new Intent(getBaseContext(), SystemOrderActivity.class);
		intent.putExtra("type", "download");
		startActivity(intent);
	}

	public void settingOnClick(View view) {
		Intent intent = new Intent(getBaseContext(), SettingActivity.class);
		startActivity(intent);
	}

	public void netOnClick(View view) {
		Intent intent = new Intent(getBaseContext(), NetActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			exitByTwoClick();
		}
		return true;
	}

	private boolean isExit = false;

	private void exitByTwoClick() {
		Timer tExit = null;
		if (!isExit) {
			isExit = true;
			Toast.makeText(getBaseContext(), "再按一次退出", Toast.LENGTH_SHORT)
					.show();
			tExit = new Timer();
			tExit.schedule(new TimerTask() {

				public void run() {
					isExit = false;
				}
			}, 2000);
		} else {
			finish();
			System.exit(0);
		}
	}

	private void initBroadCast() {
		IntentFilter filterUpdate = new IntentFilter();
		filterUpdate.addAction("change_name");
		if (receiverUpdate == null) {
			receiverUpdate = new UpdateBroadcastReceiver();
		}
		registerReceiver(receiverUpdate, filterUpdate);

		IntentFilter filterOrderPopu = new IntentFilter();
		filterOrderPopu.addAction("updata_order_windows");
		if (orderPopuBroadcast == null) {
			orderPopuBroadcast = new UpdateOrderPopuwindBroadcast();
		}
		registerReceiver(orderPopuBroadcast, filterOrderPopu);

		IntentFilter filterWriteFileOrder = new IntentFilter();
		filterWriteFileOrder.addAction("write_file_order");
		if (writeFileOrder == null) {
			writeFileOrder = new WriteFileOrderBroadcastReceiver();
		}
		registerReceiver(writeFileOrder, filterWriteFileOrder);

		IntentFilter filterWriteFileMusic = new IntentFilter();
		filterWriteFileMusic.addAction("write_file_music");
		if (writeFileMusic == null) {
			writeFileMusic = new WriteFileMusicBroadcastReceiver();
		}
		registerReceiver(writeFileMusic, filterWriteFileMusic);

		IntentFilter filterDownlaod = new IntentFilter();
		filterDownlaod.addAction("downloaded");
		if (downloadFinish == null) {
			downloadFinish = new downloadFinishBroadcastReceiver();
		}
		registerReceiver(downloadFinish, filterDownlaod);
	}

	class downloadFinishBroadcastReceiver extends BroadcastReceiver {

		public void onReceive(Context context, Intent intent) {
			Toast.makeText(getBaseContext(), "下载完成", Toast.LENGTH_LONG).show();
		}

	}

	class WriteFileOrderBroadcastReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			startService(new Intent(getBaseContext(),
					WriteFileService_Order.class));
		}
	}

	class WriteFileMusicBroadcastReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			startService(new Intent(getBaseContext(),
					WriteFileService_Music.class));
		}
	}

	class UpdateBroadcastReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			updataMusicStatus(true);
			updatePauseImage();
			updateMusicName();
		}
	}

	class UpdateOrderPopuwindBroadcast extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			orderPopupWindow.addPopupWindowElement(music.getOrderList()
					.getLast());
		}

	}

	private void updateMusicName() {
		musicNameView.setText(music.getCurrentMusicInfor().getMusicName());
	}

	private void updatePauseImage() {
		if (musicStatus.isPlaying()) {

			pause.setBackgroundResource(R.drawable.localmusic_contral_pause_stop_x);
		} else {
			pause.setBackgroundResource(R.drawable.menu_contral_pause_start_x);
		}
	}

	private void updataMusicStatus(boolean status) {
		musicStatus.setPlaying(status);
	}

}
