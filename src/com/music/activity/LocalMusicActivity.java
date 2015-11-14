package com.music.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

import com.music.activity_util.impl.ContralMusic;
import com.music.activity_util.inter.ContralMusicIf;
import com.music.adapter.PlayerViewPagerAdapter;
import com.music.enums.PlayerEnum;
import com.music.musicapp.R;
import com.music.util.impl.InitUtil;
import com.music.util.impl.MusicBase;
import com.music.util.impl.MusicInstrumentStatus;
import com.music.util.inter.Music;
import com.music.view.fartory.MusicLetterRelativeFactory;
import com.music.view.fartory.MusicLetterRollFactory;
import com.music.view.fartory.OrderPopupWindowFactory;
import com.music.view.impl.MusicRowRelativeAbstract;
import com.music.view.impl.MusicRowRelativeControl;
import com.music.view.impl.OrderPopupWindow;
import com.music.view.inter.MusicLetterRelative;
import com.music.view.inter.MusicLetterRoll;
import com.music.view.inter.MusicRowRelative;

public class LocalMusicActivity extends Activity {
	/**
	 * 点击效果线程睡眠时间
	 * */
	private static final int SLEEPTIME = 150;

	private static final int TOPDISTANCE = 400;

	/**
	 * 控制字母表切换线程的运行或者停止的标志
	 * */
	private boolean letterThread = false;

	/**
	 * 左侧歌单LinearLayout
	 * */
	private LinearLayout listContext = null;

	/**
	 * 右侧字母表
	 * */
	private LinearLayout letters = null;

	/**
	 * 暂停按钮
	 * */
	private ImageView pause = null;
	private MusicInstrumentStatus musicStatus = MusicInstrumentStatus
			.getInstrumentStatus();
	private UpdateBroadcastReceiver receiverUpdate = null;

	private AddBroadcastReceiver addReceiver = null;

	private RemoveBroadcastReceiver removeReceiver = null;

	/**
	 * 滚动条<br>
	 * 重新加载数据、调整字母表有用
	 * */
	private ScrollView scrollView = null;

	/**
	 * scrollLinearHeight：每一组音乐在滚动条ScrollView中所占的高度空间,每一组都有一个单独的高度，用着计算height<br>
	 * height:每一组音乐的顶端距离ScrollView顶端的距离,每一组有一个单独的高度
	 * */
	private LinkedList<Integer> scrollLinearHeight = new LinkedList<Integer>();
	private LinkedList<Integer> height = null;

	/**
	 * 可以的字母Id列表,字母转换成数字保存的，如A保存的是65
	 * */
	private LinkedList<Integer> letterIdList = new LinkedList<Integer>();
	/**
	 * 字母表的布局文件的引用
	 * */
	private LinkedList<RelativeLayout> letterList = new LinkedList<RelativeLayout>();

	/**
	 * ViewPager
	 * */
	private ViewPager viewPager = null;
	private View pagerMusic, pagersinger, pagerspecial, pagerfile;

	/**
	 * 音乐播放控制，加载部分歌单
	 * */
	private ContralMusicIf contralMusic = null;

	private LinkedList<TextView> options = new LinkedList<TextView>();

	private OrderPopupWindow orderPopupWindow = null;

	private Map<String, LinkedList<Integer>> mapLetterList = null;

	private Map<String, LinearLayout> mapLetterLayout = new HashMap<String, LinearLayout>();

	private Activity me = null;

	private LinkedList<MusicRowRelative> rows = new LinkedList<MusicRowRelative>();

	private TextView musicName = null;

	private SeekToReceiver seekToReceiver = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.localmusic_activity);
		contralMusic = new ContralMusic(this);

		initOrderPopWindow();

		initViewPager();

		initView();

		initBroadCast();

		/**
		 * 延迟加载数据
		 * */
		new Timer().schedule(new TimerTask() {

			public void run() {
				runOnUiThread(new Runnable() {
					public void run() {
						updateContext();
					}
				});
			}
		}, 2000);
		me = this;
	}

	/**
	 * 1、更新暂停按钮图片<br>
	 * 2、启动监视线程<br>
	 * */
	protected void onResume() {
		updatePauseImage();
		startChangeLetterThread();
		updateBackground();
		updateMusicName();
		super.onResume();
	}

	/**
	 * 注销广播
	 * */
	protected void onDestroy() {
		super.onDestroy();
		if (receiverUpdate != null) {
			unregisterReceiver(receiverUpdate);
		}

		if (addReceiver != null) {
			unregisterReceiver(addReceiver);
		}

		if (removeReceiver != null) {
			unregisterReceiver(removeReceiver);
		}

		if (seekToReceiver != null) {
			unregisterReceiver(seekToReceiver);
		}
	}

	/**
	 * 停止监视线程
	 * */
	protected void onPause() {
		stopChangeLetterThread();
		super.onPause();
	}

	private void initOrderPopWindow() {
		orderPopupWindow = new OrderPopupWindowFactory().create(this);
	}

	private void initView() {
		pause = (ImageView) findViewById(R.id.net_bottom_pause);

		musicName = (TextView) findViewById(R.id.net_bottom_musicName);

		TextView option_music = (TextView) findViewById(R.id.net_recommend);
		TextView option_singer = (TextView) findViewById(R.id.net_sort);
		TextView option_file = (TextView) findViewById(R.id.net_classify);
		TextView option_special = (TextView) findViewById(R.id.net_order);

		options.add(option_music);
		options.add(option_singer);
		options.add(option_special);
		options.add(option_file);

		option_music.setOnClickListener(new OptionOnClickListener(0));
		option_singer.setOnClickListener(new OptionOnClickListener(1));
		option_special.setOnClickListener(new OptionOnClickListener(2));
		option_file.setOnClickListener(new OptionOnClickListener(3));

	}

	/**
	 * 初始化viewPager 1、设定scrollView触摸监听，用着继续加载歌单
	 * */
	private void initViewPager() {

		viewPager = (ViewPager) findViewById(R.id.net_viewPager_rela);
		/**
		 * 
		 * 
		 * */
		ArrayList<View> viewPagerList = new ArrayList<View>();

		LayoutInflater flater = getLayoutInflater();

		pagerMusic = flater.inflate(R.layout.localmusic_viewpager_music, null);

		pagersinger = flater
				.inflate(R.layout.localmusic_viewpager_singer, null);

		pagerspecial = flater.inflate(R.layout.localmusic_viewpager_special,
				null);

		pagerfile = flater.inflate(R.layout.localmusic_viewpager_file, null);

		scrollView = (ScrollView) pagerMusic.findViewById(R.id.menu_scroll);

		viewPagerList.add(pagerMusic);

		viewPagerList.add(pagersinger);

		viewPagerList.add(pagerspecial);

		viewPagerList.add(pagerfile);

		viewPager.setAdapter(new PlayerViewPagerAdapter(viewPagerList));

		viewPager.setCurrentItem(0);

		/**
		 * 初始化前n个字母的歌曲<br>
		 * musicPager的初始化
		 * */
		listContext = (LinearLayout) pagerMusic
				.findViewById(R.id.netmusic_context);

		RelativeLayout topFunction = (RelativeLayout) flater.inflate(
				R.layout.top_function, null);

		listContext.addView(topFunction);

		initListContext(0, InitUtil.initFirstInitEndIndex());

		initLetters();

	}

	private void initLetters() {
		letters = (LinearLayout) pagerMusic.findViewById(R.id.list_letters);
		MusicLetterRoll roll = new MusicLetterRollFactory().create(this,
				letters);
		roll.roduction();
		letterList = roll.getLetterList();
	}

	/**
	 * 创建每一组歌曲的相对高度
	 * */
	private void createHeight() {
		height = new LinkedList<Integer>();
		height.add(0);
		int tmp = 0;
		for (int i = 0; i < scrollLinearHeight.size(); i++) {
			tmp = 0;
			for (int j = 0; j <= i; j++) {
				tmp = tmp + scrollLinearHeight.get(j);
			}
			height.add(tmp);
		}
	}

	/**
	 * 更新歌曲<br>
	 * 1、关闭监视线程<br>
	 * 2、初始化后面的内容<br>
	 * 3、再次启动线程
	 * */
	private void updateContext() {
		stopChangeLetterThread();

		initListContext(InitUtil.initFirstInitEndIndex(), 26);

		startChangeLetterThread();

	}

	/**
	 * 启动监视线程<br>
	 * 1、检查位置<br>
	 * 2、运行到UI线程并执行相应的更改
	 * */
	private void startChangeLetterThread() {
		letterThread = true;

		new Thread(new Runnable() {

			public void run() {
				/**
				 * 循环监视线程
				 * */
				while (letterThread) {

					Integer scrollY = scrollView.getScrollY() + TOPDISTANCE;

					/**
					 * 循环检查满足条件的
					 * */
					for (int i = 0; i < height.size() - 1; i++) {

						/**
						 * 这里的i（index）和letterIdList的坐标正好一一对应，
						 * */
						final int index = i;
						/**
						 * 如果满足条件则执行更改操作并退出本次循环
						 * */
						if (scrollY > height.get(i)
								&& scrollY < height.get(i + 1)) {

							/**
							 * UI线程循环更改
							 * */
							runOnUiThread(new Runnable() {
								/**
								 * 更改线程
								 * */
								public void run() {
									for (RelativeLayout letter : letterList) {
										// letter.setBackgroundResource(R.drawable.list_letter_background_white);
										TextView text = (TextView) letter
												.getChildAt(0);
										text.setTextColor(Color.WHITE);
										text.setTextSize(12);
									}

									RelativeLayout letter = (RelativeLayout) letters
											.findViewById(letterIdList
													.get(index));

									// letter.setBackgroundResource(R.drawable.list_letter_background_blue);
									TextView text = (TextView) letter
											.getChildAt(0);
									text.setTextColor(Color.GREEN);
									text.setTextSize(15);
								}
							});

							break;
						}

					}
					try {
						Thread.sleep(SLEEPTIME * 2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}).start();
	}

	/**
	 * 停止监听线程
	 * */
	private void stopChangeLetterThread() {
		letterThread = false;
	}

	/**
	 * id用着判断具体的某个页面
	 * */
	class OptionOnClickListener implements OnClickListener {
		int id = 0;

		public OptionOnClickListener(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}

		public void onClick(View seletced) {
			for (TextView view : options) {
				view.setBackgroundResource(R.drawable.list_option_backgroud_default);
			}
			seletced.setBackgroundResource(R.drawable.list_option_backgroud_selected);
			viewPager.setCurrentItem(id);
		}
	}

	/**
	 * 初始化歌曲列表<br>
	 * start：开始字母 end：结束字母<br>
	 * */
	private void initListContext(int start, int end) {

		MusicBase music = MusicBase.createMusic();
		/**
		 * 获取音乐音乐搜字母索引表:A对应的歌曲;B对应的歌曲等....
		 * */
		mapLetterList = music.getLetterTable();

		/**
		 * title
		 * */
		RelativeLayout letterRela = new RelativeLayout(getBaseContext());
		LayoutParams letterRelaLayout = new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		letterRelaLayout.setMargins(0, 0, 10, 0);
		TextView other = new TextView(getBaseContext());
		other.setText("测试-其它操作");
		other.setTextSize(30);
		LayoutParams otherLayout = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		otherLayout.addRule(RelativeLayout.CENTER_IN_PARENT);
		letterRela.addView(other, otherLayout);

		/**
		 * for循环初始化 并不包含end这一个
		 * */
		for (int i = start; i < end; i++) {
			int number = i + 65;
			char word = (char) number;
			LinkedList<Integer> list = mapLetterList.get(Character
					.toString(word));

			/**
			 * 满足进行以下操作:<br>
			 * 
			 * 1、用获取的word：首字母 list：歌单初始化剩余的歌曲 2、增加aScrollLinearHeight
			 * 3、添加LetterIdList
			 * */
			if (list != null && list.size() >= 1) {
				/**
				 * 初始化一组歌曲的方法
				 * */
				MusicLetterRelative letter = new MusicLetterRelativeFactory()
						.create(this, Character.toString(word), list);

				letter.setOrderPopupWindow(orderPopupWindow);

				listContext.addView(letter.getMusicView(),
						letter.getMusicLayout());

				addScrollLinearHeight(letter.getLetterHeight());

				addLetterIdList(number);

				LinkedList<MusicRowRelative> ll = letter.getRowList();

				for (MusicRowRelative row : ll) {
					rows.add(row);
				}

				mapLetterLayout.put(Character.toString(word),
						(LinearLayout) letter.getMusicView());

			}
		}
		createHeight();
	}

	private void addLetterIdList(Integer number) {
		letterIdList.add(number);
	}

	private void addScrollLinearHeight(Integer height) {
		scrollLinearHeight.add(height);
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
		this.finish();
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

	class UpdateBroadcastReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			updataMusicStatus(true);
			updatePauseImage();
			updateBackground();
			updateMusicName();
		}
	}

	private void updateMusicName() {
		Music music = MusicBase.createMusic();
		musicName.setText(music.getCurrentMusicInfor().getMusicName());
	}

	/**
	 * 更改当前正在播放音乐背景
	 * */
	private void updateBackground() {
		Music music = MusicBase.createMusic();
		for (MusicRowRelative row : rows) {
			MusicRowRelativeAbstract ra = (MusicRowRelativeAbstract) row;
			if (ra.getId() == music.getCurrentMusicInfor().getLocalId()) {
				row.getMusicView().setBackgroundResource(
						R.drawable.list_context_background_text_click);
			} else {
				row.getMusicView().setBackgroundResource(
						R.drawable.list_context_background_text);
			}
		}
	}

	private int showId = -1;

	public int getShowId() {
		return showId;
	}

	public void setShowId(int contralId) {
		this.showId = contralId;
	}

	class AddBroadcastReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			int id = intent.getIntExtra("id", -1);
			if (getShowId() > -1) {
				remove(getShowId(), false);
				setShowId(-1);
			}
			add(id);
		}
	}

	private void add(int id) {
		for (String key : mapLetterList.keySet()) {
			boolean isBreak = false;
			for (int i = 0; i < mapLetterList.get(key).size(); i++) {
				if (mapLetterList.get(key).get(i) == id) {
					isBreak = true;
					LinearLayout lin = mapLetterLayout.get(key);
					lin.addView(
							new MusicRowRelativeControl(me, id).getMusicView(),
							i + 2);
					setShowId(id);
					break;
				}
			}
			if (isBreak) {
				break;
			}
		}
	}

	private void remove(int id, boolean deleteMusic) {
		for (String key : mapLetterList.keySet()) {
			boolean isBreak = false;
			for (int i = 0; i < mapLetterList.get(key).size(); i++) {
				Integer cid = mapLetterList.get(key).get(i);
				if (cid == id) {
					isBreak = true;
					LinearLayout lin = mapLetterLayout.get(key);
					lin.removeViewAt(i + 2);
					if (deleteMusic) {
						lin.removeViewAt(i + 1);
						/**
						 * 这句代码并不需要,在后面进行统一的操作
						 * */
					}
					setShowId(-1);
					break;
				}
			}
			if (isBreak) {
				break;
			}
		}
	}

	class RemoveBroadcastReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			int id = intent.getIntExtra("id", 0);
			remove(id, intent.getBooleanExtra("deleteMusic", false));
		}
	}

	private void initBroadCast() {
		IntentFilter filterUpdate = new IntentFilter();
		filterUpdate.addAction("change_name");
		if (receiverUpdate == null) {
			receiverUpdate = new UpdateBroadcastReceiver();
		}
		registerReceiver(receiverUpdate, filterUpdate);

		IntentFilter filterAdd = new IntentFilter();
		filterAdd.addAction("add_d");
		if (addReceiver == null) {
			addReceiver = new AddBroadcastReceiver();
		}
		registerReceiver(addReceiver, filterAdd);

		IntentFilter filterRemove = new IntentFilter();
		filterRemove.addAction("remove_d");
		if (removeReceiver == null) {
			removeReceiver = new RemoveBroadcastReceiver();
		}
		registerReceiver(removeReceiver, filterRemove);

		IntentFilter filterSeekTo = new IntentFilter();
		filterSeekTo.addAction("local_seek_to");
		if (seekToReceiver == null) {
			seekToReceiver = new SeekToReceiver();
		}
		registerReceiver(seekToReceiver, filterSeekTo);

	}

	private void startMusicPlayerService(int type) {
		contralMusic.startMusicPlayerService(type);
	}

	class SeekToReceiver extends BroadcastReceiver {

		public void onReceive(Context context, Intent intent) {

			char word = intent.getCharExtra("word", 'A');

			System.out.println(word);
			int point = 0;
			for (int i = 65; i <= 91; i++) {
				char ch = (char) i;
				LinkedList<Integer> list = mapLetterList.get(Character
						.toString(ch));
				if (word <= ch) {
					break;
				}
				
				if (list == null || list.size() == 0) {
					continue;
				} else {
					point = point + InitUtil.getTitleheight()
							+ InitUtil.getTextheight() * (list.size());
				}

			}

			if (point < 0) {
				point = 0;
			}
			scrollView.scrollTo(0, point);
		}

	}

}
