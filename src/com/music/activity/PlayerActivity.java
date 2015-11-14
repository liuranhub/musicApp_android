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
import com.music.util.impl.MusicBase;
import com.music.util.impl.MusicInformation;
import com.music.util.impl.MusicInstrumentStatus;
import com.music.util.impl.MusicScrollLyric;
import com.music.util.impl.MusicSeekBarImpl;
import com.music.util.impl.StudyMusic;
import com.music.util.impl.Time;
import com.music.util.inter.Music;
import com.music.util.inter.MusicSeekBar;
import com.music.view.fartory.MusicRowRelativePlayerFactory;
import com.music.view.fartory.OrderPopupWindowFactory;
import com.music.view.impl.ModePopupWindow;
import com.music.view.impl.OrderPopupWindow;
import com.music.view.inter.MusicRowRelative;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ����ṹ��<br>
 * 1����������<br>
 * 2�����ǵķ���<br>
 * 3����ʼ������<br>
 * 4����������<br>
 * 5���Զ��巽��<br>
 * 6��������<br>
 * 7���Զ�����<br>
 * */
public class PlayerActivity extends Activity {

	/**
	 * viewPager ���һ����л�����<br>
	 * vp_firstImage, vp_secondImage, vp_threadImage �ֱ������������������ť pagerFirst,
	 * pagerSecond, pagerThread �ֱ������������
	 * */
	private ViewPager viewPager = null;
	private ImageView vp_firstImage, vp_secondImage, vp_threadImage;
	private View pagerFirst, pagerSecond, pagerThread;

	/**
	 * pause ��ͣ��ť<br>
	 * like ƫ�ð�ť<br>
	 * totleTime��alreadyTime �ܵ�ʱ����Ѿ����ŵ�ʱ���TextView<br>
	 * imageMode ����ģʽ��ť<br>
	 * */
	private ImageView pause = null;
	private ImageView like = null;
	private TextView totleTime = null;
	private TextView alreadyTime = null;
	private ImageView imageMode = null;
	private TextView musicName = null;

	private MusicScrollLyric musicScroll = null;

	/**
	 * musicListView ��ViewPager�еò����б�Ĺ����������<br>
	 * initViewPagerFirst()�������崴������б�
	 * */
	private LinearLayout musicListView = null;

	/**
	 * relatives ����musicListView�е�ÿһ�ÿһ�����һ�׸�
	 * */
	private ArrayList<RelativeLayout> relatives = new ArrayList<RelativeLayout>();

	/**
	 * popupWindow ����ģʽ����ʽ����
	 * */
	private ModePopupWindow modePopupWindow = null;

	private OrderPopupWindow orderPopupWindow = null;

	/**
	 * Music����
	 * */
	private Music music = MusicBase.createMusic();

	/**
	 * musicStatus ���ֲ�������״̬
	 * */
	private MusicInstrumentStatus musicStatus = MusicInstrumentStatus
			.getInstrumentStatus();

	/**
	 * SeekBar
	 * */
	private MusicSeekBar seekBar = null;

	/**
	 * ÿ��������һ�ε��߳̽�����־
	 * */
	private boolean oneSecondSign = false;

	private BroadcastReceiver receiverUpdate = null;

	private ContralMusicIf contralMusic = null;

	private StudyMusic study = StudyMusic.createStudyMusic();

	private View study_start , study_end ,study_stop ;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.player_activity);
		initView();
		initOrderPopWindow();
		initViewPager();
		initModePopupWindow();
		updateMusicContext();
		contralMusic = new ContralMusic(this);
		startService(new Intent(this, AutoPlayService.class));
		initBroadCast();
	}

	protected void onResume() {
		super.onResume();
		startThreadForOneSecond();
		updateInfromationForResume();

		setImageMode(music.getMode() + 1);
	}

	protected void onPause() {
		super.onPause();
		stopOneSecondThread();
	}

	protected void onDestroy() {
		if (receiverUpdate != null) {
			unregisterReceiver(receiverUpdate);
		}
		super.onDestroy();
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * initVp_imageView
	 * */
	private void initView() {
		vp_firstImage = (ImageView) findViewById(R.id.player_page_frist);
		vp_secondImage = (ImageView) findViewById(R.id.player_page_second);
		vp_threadImage = (ImageView) findViewById(R.id.player_page_thread);
		pause = (ImageView) findViewById(R.id.player_stopMusic);
		alreadyTime = (TextView) findViewById(R.id.player_alreadyPlayTime);
		totleTime = (TextView) findViewById(R.id.player_totalPlayTime);
		imageMode = (ImageView) findViewById(R.id.playing_playmode);

		musicName = (TextView) findViewById(R.id.player_musicname);
		musicName.setSingleLine(true);

		vp_firstImage.setOnClickListener(new ImageViewOnClickListenre(0));
		vp_secondImage.setOnClickListener(new ImageViewOnClickListenre(1));
		vp_threadImage.setOnClickListener(new ImageViewOnClickListenre(2));
		vp_secondImage.setImageResource(R.drawable.bf_player_page_clear);

		SeekBar bar = (SeekBar) findViewById(R.id.player_seekbar);
		seekBar = new MusicSeekBarImpl(bar);
		seekBar.changeListener(new OnSeekBarChangeListener() {

			public void onStopTrackingTouch(SeekBar seekBar) {

			}

			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				if (fromUser) {
					music.getPlayer().seekTo(progress);
				}
			}
		});
	}

	private void initModePopupWindow() {
		modePopupWindow = new ModePopupWindow(this, R.layout.player_style);
		modePopupWindow.setWidth(300);
		modePopupWindow.setHight(300);

		View popLayout = modePopupWindow.getView();
		View first = popLayout.findViewById(R.id.player_mode_first);
		View second = popLayout.findViewById(R.id.player_mode_second);
		View thread = popLayout.findViewById(R.id.player_mode_thread);

		first.setOnClickListener(new popWinOnElementOnClickListner(1));
		second.setOnClickListener(new popWinOnElementOnClickListner(2));
		thread.setOnClickListener(new popWinOnElementOnClickListner(3));

		modePopupWindow.addViewListElement(first).addViewListElement(second)
				.addViewListElement(thread);
		modePopupWindow.changeColor(music.getMode() + 1);
	}

	private void initOrderPopWindow() {
		orderPopupWindow = new OrderPopupWindowFactory().create(this);
	}

	/**
	 * ��ʼ��ViewPager
	 * */
	private void initViewPager() {
		viewPager = (ViewPager) findViewById(R.id.player_viewPager);
		ArrayList<View> viewPagerList = new ArrayList<View>();
		LayoutInflater flater = getLayoutInflater();

		pagerFirst = flater.inflate(R.layout.player_viewpager_first, null);
		pagerSecond = flater.inflate(R.layout.player_viewpager_second, null);
		pagerThread = flater.inflate(R.layout.player_viewpager_thread, null);
		
		study_start = pagerThread.findViewById(R.id.start);
		study_end = pagerThread.findViewById(R.id.end);
		study_stop = pagerThread.findViewById(R.id.stop);

		ScrollView scroll = (ScrollView) pagerSecond
				.findViewById(R.id.musicLyric);

		musicScroll = new MusicScrollLyric(scroll, this);

		viewPagerList.add(pagerFirst);
		viewPagerList.add(pagerSecond);
		viewPagerList.add(pagerThread);
		viewPager.setAdapter(new PlayerViewPagerAdapter(viewPagerList));

		/**
		 * viewPager��ʼҳ��
		 * */
		viewPager.setCurrentItem(1);

		/**
		 * viewPager����
		 * */
		viewPager.setOnPageChangeListener(new OnPlayerViewPagerChangeListner());

		/**
		 * ��ʼ����һ��pager
		 * */
		initViewPagerFirst();

		like = (ImageView) pagerSecond.findViewById(R.id.player_like);
	}

	/**
	 * ��ʼ����һ��viewPager
	 * */
	private void initViewPagerFirst() {
		// ��ȡpagerFirst�еø����б������
		musicListView = (LinearLayout) pagerFirst
				.findViewById(R.id.player_pager_first_musicList);
		musicListView.layout(100, 0, 100, 0);
	}

	private void updateMusicContext() {
		musicListView.removeAllViews();

		relatives.clear();

		initContext(0, 10);

		new Timer().schedule(new TimerTask() {

			public void run() {
				runOnUiThread(new Runnable() {
					public void run() {
						initContext(10, music.getMusicList().size());
					}
				});
			}
		}, 1000);
	}

	private void initContext(int start, int end) {
		LinkedList<MusicInformation> musicList = music.getMusicList();
		for (int i = start; i < end; i++) {
			MusicRowRelative row = new MusicRowRelativePlayerFactory().create(
					this, musicList.get(i).getLocalId());
			row.setOrderPopupWindow(orderPopupWindow);
			musicListView.addView(row.getMusicView(), row.getMusicLayout());

			relatives.add((RelativeLayout) row.getMusicView());
		}
	}

	/**
	 * ��ȡÿ��һ��ѭ����seekBar�̵߳�״̬<br>
	 * 
	 * @return false ������ǰ�߳�
	 * @return true �������е�ǰ�߳�
	 * */
	private boolean isOneSecondSign() {
		return oneSecondSign;
	}

	/**
	 * ����ÿ��һ��ѭ����seekBar�̵߳�״̬<br>
	 * 
	 * @param oneSecondSign
	 *            false,�߳̽������� ���������̵߳�ʱ����Ҫ���ó�Ϊtrue
	 * */
	private void stopOneSecondThread() {
		this.oneSecondSign = false;
	}

	private void startOneSecondThread() {

		this.oneSecondSign = true;
	}

	/**
	 * ��ͣ���߲��Ű�ť����<br>
	 * 1������MusicInstrumentStatus�е÷����������ֲ�������״̬<br>
	 * 2����ͣ���ֲ���<br>
	 * 3������MusicSeekBar����Ϣ<br>
	 * */
	public void pauseMusicOnClick(View view) {
		stopOrStartMusicUpdate();
	}

	/**
	 * ��һ��
	 * */
	public void nextMusicOnClick(View view) {
		startMusicPlayerService(PlayerEnum.NEXT.ordinal());
		nextOrPreviousUpdate();
	}

	/**
	 * ��һ��
	 * */
	public void previousMusicOnClick(View view) {
		startMusicPlayerService(PlayerEnum.PREVIOUS.ordinal());
		nextOrPreviousUpdate();
	}

	public void likeButtonOnClick(View view) {
		changeLikeView();
	}

	public void downloadOnClick(View view) {
		Toast.makeText(getBaseContext(), "���ع��ܻ�û����ɣ�", Toast.LENGTH_SHORT)
				.show();
	}

	public void shareOnClick(View view) {
		Toast.makeText(getBaseContext(), "�����ܻ�û�����", Toast.LENGTH_SHORT)
				.show();
	}

	public void contralLyricOnClick(View view) {
		Toast.makeText(getBaseContext(), "��ʹ��ܻ�û�����", Toast.LENGTH_SHORT)
				.show();
	}

	public void playingOrderOnClick(View view) {
		orderPopupWindow.showAsDropDown(view);
		orderPopupWindow.updateStartPoint(view, 0, 30);
		autoClosePopupWindow(orderPopupWindow, 15000);
	}

	public void playModeOnClick(View view) {
		modePopupWindow.showAsDropDown(view);
		modePopupWindow.updateStartPoint(view, -19, -20);
		autoClosePopupWindow(modePopupWindow, 8000);
	}

	public void backMenuOnClick(View view) {
		Intent intent = new Intent(this, MenuActivity.class);
		startActivity(intent);
		this.finish();
	}

	public void singEndOnClick(View view) {
		view.setClickable(false);
		study.setEnd(music.getPlayer().getCurrentPosition());
		study.setSinging(true);
		study_stop.setClickable(true);
		study_start.setClickable(false);
		((Button)study_end).setTextColor(Color.GRAY);
		((Button)study_start).setTextColor(Color.GRAY);
		((Button)study_stop).setTextColor(Color.WHITE);
		startSingThread();
	}

	public void singStartOnClick(View view) {
		((Button)study_end).setTextColor(Color.WHITE);
		study_end.setClickable(true);
		
		study.setStart(music.getPlayer().getCurrentPosition());
	}
	
	private void startSingThread(){
		music.getPlayer().seekTo(study.getStart());
		new Thread(new Runnable() {
			public void run() {
				while(study.isSinging()){
					if(music.getPlayer().getCurrentPosition()>=study.getEnd()){
						music.getPlayer().seekTo(study.getStart());
//						startMusicPlayerService(PlayerEnum.STOP.ordinal());
//						stopOrStartMusicUpdate();
					}
				}
			}
		}).start();
	}
	

	public void singStopOnClick(View view) {
		study_end.setClickable(false);
		study_start.setClickable(true);
		study_stop.setClickable(false);
		((Button)study_end).setTextColor(Color.GRAY);
		((Button)study_start).setTextColor(Color.WHITE);
		((Button)study_stop).setTextColor(Color.GRAY);
		study.setSinging(false);
	}

	private void autoClosePopupWindow(final ModePopupWindow popu, final int time) {
		/**
		 * �رյ��̱߳���������UI�߳���
		 * */
		if (popu != null) {
			popu.autoClosePopupWindow(time);
		}
	}

	/**
	 * �����һ�׻�����һ�׵�ʱ����Ľ�����Ϣ<br>
	 * 1������MusicInstrumentStatus��״̬Ϊ���ڲ��ŵ�״̬<br>
	 * 2��˯��500���룬ʵ�ֶ�̬���Ч��<br>
	 * 3������MusicSeekBar������<br>
	 * 4������pause��ť��ͼ��
	 * */
	private void nextOrPreviousUpdate() {
		pause.setImageResource(R.drawable.bf_player_pause_start);
		pause.refreshDrawableState();
		try {
			Thread.sleep(350);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pause.setImageResource(R.drawable.bf_player_pause_stop);
	}

	/**
	 * 1������MusicInstrumentStatus�е÷����������ֲ�������״̬<br>
	 * 2����ͣ���ֲ���<br>
	 * 3������MusicSeekBar����Ϣ<br>
	 * 4������pause��ťͼ��
	 * */
	private void stopOrStartMusicUpdate() {
		changeMusicStatus();
		if (musicStatus.isPlaying()) {
			startMusicPlayerService(PlayerEnum.START.ordinal());
		} else {
			startMusicPlayerService(PlayerEnum.STOP.ordinal());
		}
		updatePauseButton();
	}

	/**
	 * �������ֵĲ���״̬
	 * */
	private void changeMusicStatus() {
		if (musicStatus.isPlaying()) {
			updataMusicStatus(false);
		} else {
			updataMusicStatus(true);
		}
	}

	/**
	 * ������̨���񣨳����л�������
	 * */
	private void startMusicPlayerService(int type) {
		contralMusic.startMusicPlayerService(type);
	}

	/**
	 * 
	 * */
	private void updateInfromationForBroad() {
		updataMusicStatus(true);
		setMusicName(music.getCurrentMusicInfor().getMusicName());
		resetSeekBar();

		resetLikeView();
		updatePagerFirst();
		updatePauseButton();
		updateLyric();
		
	}
	
	/**
	 * @param type 1����������Ϣ ��2�ָ�����״̬
	 * 
	 * */
	private void resetStudy(int type){
		if(type==1){
			study.setSinging(false);
			study_start.setClickable(true);
			study_end.setClickable(false);
			study_stop.setClickable(false);
			((Button)study_start).setTextColor(Color.WHITE);
			((Button)study_end).setTextColor(Color.GRAY);
			((Button)study_stop).setTextColor(Color.GRAY);
		}else{
			if(study.isSinging()){
				study_start.setClickable(false);
				study_end.setClickable(false);
				study_stop.setClickable(true);
				((Button)study_start).setTextColor(Color.GRAY);
				((Button)study_end).setTextColor(Color.GRAY);
				((Button)study_stop).setTextColor(Color.WHITE);
			}
		}
		
	}

	private void updateLyric() {
		MusicInformation inf = music.getCurrentMusicInfor();
		musicScroll.clearScroll();
		if (inf.getLrc() != null) {
			musicScroll.setLyric(inf.getLrc());
		}
	}

	private void atuoScollLyric(int time) {
		musicScroll.scrollTo(time);
	}

	private void updateInfromationForResume() {
		setMusicName(music.getCurrentMusicInfor().getMusicName());
		resetSeekBar();
		resetLikeView();
		updatePauseButton();
		updateLyric();
	}

	private void updataMusicStatus(boolean status) {
		musicStatus.setPlaying(status);
	}

	/**
	 * ����musicName
	 * */
	private void setMusicName(final String nameText) {
		runOnUiThread(new Runnable() {
			public void run() {
				musicName.setText(nameText);
			}
		});
	}

	/**
	 * ����MusicSeekBar������<br>
	 * ���õ����Զ���seekBar������
	 * */
	private void resetSeekBar() {
		seekBar.resetSeekBar(music.getPlayer().getDuration());
	}

	/**
	 * seekBar���ƶ�
	 **/
	private void updateSeekBar() {
		seekBar.setProgress(music.getPlayer().getCurrentPosition());
	}

	/**
	 * �ı�likeͼƬ��״̬���л��������Զ��ı�
	 * */
	private void resetLikeView() {
		if (music.getCurrentMusicInfor().isLike()) {
			like.setImageResource(R.drawable.bf_player_like_red);
		} else {
			like.setImageResource(R.drawable.bf_player_like);
		}
	}

	/**
	 * �ı�likeͼƬ��״̬���û�����Ǹı�
	 * */
	private void changeLikeView() {
		if (music.getCurrentMusicInfor().isLike()) {
			like.setImageResource(R.drawable.bf_player_like);
			music.getMusicList()
					.get(music.getCurrentMusicInfor().getLocalId() - 1)
					.setLike(false);
		} else {
			like.setImageResource(R.drawable.bf_player_like_red);
			music.getMusicList()
					.get(music.getCurrentMusicInfor().getLocalId() - 1)
					.setLike(true);
		}
	}

	/**
	 * �������һ������һ�����Զ�����ʱ���²����б����ʾ״̬
	 * */
	private void updatePagerFirst() {
		if (relatives.size() > 0) {
			for (RelativeLayout view : relatives) {
				view.setBackgroundResource(R.drawable.bf_player_pager_first_text_bg);
				ImageView image = (ImageView) view.getChildAt(2);
				image.setImageResource(R.drawable.bf_player_pager_first_playing_null);
			}

			relatives.get(music.getCurrentMusicInfor().getLocalId() - 1)
					.setBackgroundResource(
							R.drawable.bf_player_pager_first_text_bg_change);
			/*
			 * ImageView image = (ImageView) relatives.get(
			 * music.getCurrentMusicInfor().getLocalId() - 1) .getChildAt(2);
			 * 
			 * image.setImageResource(R.drawable.bf_player_pager_first_playing);
			 */
		}
	}

	/**
	 * ���pause��ťʱ�л���ť��ͼ��
	 * */
	private void updatePauseButton() {
		if (musicStatus.isPlaying()) {
			pause.setImageResource(R.drawable.bf_player_pause_stop);
		} else {
			pause.setImageResource(R.drawable.bf_player_pause_start);
		}
	}

	/**
	 * ÿ������һ�ε��߳�
	 * */
	private void startThreadForOneSecond() {

		startOneSecondThread();

		new Thread(new Runnable() {
			public void run() {
				while (isOneSecondSign() == true) {

					updateSeekBar();

					/**
					 * ÿ1000��������һ��UI�߳�
					 * */
					runOnUiThread(new Runnable() {
						public void run() {
							if (musicStatus.isPlaying()) {
								alreadyTime.setText(Time.timeToString(music
										.getPlayer().getCurrentPosition()));
								totleTime.setText(Time.timeToString(music
										.getPlayer().getDuration()));
							}
						}
					});

					/**
					 * ��λ��ת����Ϊ����
					 * */
					atuoScollLyric(music.getPlayer().getCurrentPosition());

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	private void initBroadCast() {
		IntentFilter filterUpdate = new IntentFilter();
		filterUpdate.addAction("change_name");

		receiverUpdate = new UpdateBroadcastReceiver();
		registerReceiver(receiverUpdate, filterUpdate);

	}

	class UpdateBroadcastReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			updateInfromationForBroad();
		}
	};

	class AutoPlayBroadcastReceiver extends BroadcastReceiver {

		public void onReceive(Context context, Intent intent) {
			if (musicStatus.isPlaying() && !music.getPlayer().isPlaying()) {
				startMusicPlayerService(PlayerEnum.AUTO.ordinal());
			}
		}
	}

	/**
	 * ImageViewOnClickListenre
	 * */
	class ImageViewOnClickListenre implements OnClickListener {
		private int index;

		public ImageViewOnClickListenre(int index) {
			this.index = index;
		}

		public int getIndex() {
			return index;
		}

		public void onClick(View v) {

		}
	}

	/**
	 * ViewPager����
	 * */
	class OnPlayerViewPagerChangeListner implements OnPageChangeListener {

		public void onPageScrollStateChanged(int arg0) {

		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		public void onPageSelected(int arg) {
			/**
			 * �����н��и���
			 * */
			vp_firstImage.setImageResource(R.drawable.bf_player_page_clear_not);
			vp_secondImage
					.setImageResource(R.drawable.bf_player_page_clear_not);
			vp_threadImage
					.setImageResource(R.drawable.bf_player_page_clear_not);

			switch (arg) {
			case 0:
				vp_firstImage.setImageResource(R.drawable.bf_player_page_clear);
				break;
			case 1:
				vp_secondImage
						.setImageResource(R.drawable.bf_player_page_clear);
				break;
			case 2:
				vp_threadImage
						.setImageResource(R.drawable.bf_player_page_clear);
				break;
			}
		}
	}

	/**
	 * ����ģʽpopupWindow��������¼�
	 * */
	class popWinOnElementOnClickListner implements OnClickListener {

		int id;

		public popWinOnElementOnClickListner(int id) {
			this.id = id;
		}

		public void onClick(View view) {
			setImageMode(id);
			music.setMode(id);
			modePopupWindow.changeColor(id);
			modePopupWindow.dismiss();
		}

		public int getId() {
			return id;
		}
	}

	private void setImageMode(int id) {
		if (id == 1) {
			imageMode.setImageResource(R.drawable.bf_playmode_repeate_random);
		} else if (id == 2) {
			imageMode.setImageResource(R.drawable.bf_playmode_repeate_all);
		}
		if (id == 3) {
			imageMode.setImageResource(R.drawable.bf_playmode_repeate_single);
		}
	}

}
