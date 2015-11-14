package com.music.util.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import com.music.enums.SeqEnum;
import com.music.serializable.MusicBean;
import com.music.sort.SortMusic;
import com.music.util.inter.Music;

import android.media.MediaPlayer;
import android.os.Environment;

/**
 * 单例模式的实现
 * */
public class MusicBase implements Music {

	/**
	 * 保存音乐播放的播放模式，因为和播放模式相关的类容都是在MusicImp类中进行的因此在这里面单独的加入一个属性<br>
	 * 为了防止后面的需要，这里还单独创建了一个PlayMode类
	 * */
	private static SeqEnum seqEnum = SeqEnum.random;
	private static PlayMode mode = PlayMode.createPlayMode();

	/**
	 * 保存的所有音乐信息总表，表的每一项都是一个MusicInformation，<br>
	 * 它保存了没首歌的具体信息,在initData方法中要进行初始化
	 * */
	private static LinkedList<MusicInformation> musicList = new LinkedList<MusicInformation>();

	/**
	 * player 一个MediaPlayer的具体实例，用着播放一首歌，<br>
	 * 同时提供播放音乐时的一些动态信息,更改它的dataSource属性可以完成音乐的切换
	 * */
	private static MediaPlayer player = new MediaPlayer();

	/**
	 * 为MediaPlayer提供数据源
	 * */
	private static String musicSource = "";

	/**
	 * 单例模式，为外部供获取的引用
	 * */
	private static MusicBase music;

	/**
	 * currentId 当前正在播放的引用的id，在musicList中的下标位置，<br>
	 * 可以更加它获取对应的MusicInformation对象,0<=currentId<musicList.size()
	 * */
	private static Integer currentId = 2;

	/**
	 * currentMusicInfor 为了方便操作，保存的是当前正在播放音乐的信息
	 * */
	private static MusicInformation currentMusicInfor = new MusicInformation();

	/**
	 * 字母表
	 * */
	private static Map<String, LinkedList<Integer>> letterTable = new HashMap<String, LinkedList<Integer>>();

	private static LinkedList<Integer> orderList = new LinkedList<Integer>();

	private static MusicOrderMap orderMap = (MusicOrderMap) new SerializUtil()
			.readMusicBean(InitUtil.getProfile_Order());;

	private MusicBase() {
		initData();
	}

	/**
	 * 创建Music
	 * 
	 * @return Music接口的一个实现
	 * */
	public static MusicBase createMusic() {
		if (music == null) {
			music = new MusicBase();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

		}
		return music;
	}

	/**
	 * 初始化类数据<br>
	 * 1、musicList初始化<br>
	 * 2、musicSource初始化<br>
	 * 3、currentId初始化<br>
	 * 4、player初始数据的设置
	 * */
	private void initData() {
		File dir = Environment.getExternalStorageDirectory();

		MusicBean musicBean = (MusicBean) new SerializUtil()
				.readMusicBean(InitUtil.getProfile_Music());

		if (musicBean != null) {
			musicList = musicBean.getMusicList();
			currentId = musicBean.getCurrentId();
			System.out.println(currentId);
			System.out.println(musicList.size());
			/**
			 * 问题代码,和next一起后会出现初始序号的错乱
			 * */
			if (currentId >= musicList.size()) {
				currentId = musicList.size() - 1;
			} else if (currentId < 1) {
				currentId = 1;
			}
			/**
			 * 这两个数据是冗余的，但是并没有更改
			 * */
			seqEnum = musicBean.getSeqEnum();
			mode.setModeOrder(seqEnum);
		}

		if (orderMap == null) {
			orderMap = new MusicOrderMap();
		}

		/**
		 * 代码修正，在这个地方进行一次判断，如果，musicList==null的时候才进行下面的操作，
		 * 否则代表已经用读取配置文件的方式加载了这个列表
		 * */
		if (musicList.size() < 1) {
			setMusicList(new File(dir.toString() + "/file/music"));
			setMusicList(new File(dir.toString() + "/file/music/download"));
		}

		letterTable = Words.createLetterTable(musicList);

		setMusicSource(musicList.get(currentId - 1).getFileUrl().toString());
		setCurrentMusicInfor(musicList.get(currentId - 1));
		player.reset();
		try {
			player.setDataSource(musicSource);
			player.prepare();
			next();
			Thread.sleep(100);
			previous();
			stop();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private static void setCurrentMusicInfor(MusicInformation currentMusicInfor) {
		MusicBase.currentMusicInfor = currentMusicInfor;
	}

	/**
	 * 第一次加载时创建musicList类
	 * */
	private void setMusicList(File file) {
		File[] files = file.listFiles();

		/**
		 * 创建musicList
		 * */
		for (File f : files) {
			if (!f.isDirectory()) {
				musicList.add(setOneMusicInfromation(f));
			}
		}

		/**
		 * 对musicList进行排序
		 * */
		sortMusicListForName();

		/**
		 * 排序后设置音乐文件的Id
		 * */
		int id = 0;
		for (MusicInformation music : musicList) {
			id++;
			music.setLocalId(id);
		}

	}

	/**
	 * 对播放列表进行排序
	 * */
	private void sortMusicListForName() {
		new SortMusic().sortByComparable();
	}

	/**
	 * 设置musicList中每一项MusicInformation的数据
	 * 
	 * @return MusicInformation
	 * */
	private MusicInformation setOneMusicInfromation(File f) {
		String fileStr = null;
		fileStr = f.toString();
		MusicInformation musicInf = new MusicInformation();
		MediaPlayer mp = new MediaPlayer();
		try {
			mp.setDataSource(f.toString());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		musicInf.setFileUrl(f);
		musicInf.setMusicName(fileStr.substring(fileStr.lastIndexOf("/") + 1,
				fileStr.lastIndexOf('.')));
		musicInf.setMusicTime(mp.getDuration());

		/**
		 * 简单设置是否属于下载文件
		 * */
		if (f.toString().contains("download")) {
			musicInf.setDownload(true);
		}

		return musicInf;
	}

	/**
	 * 查找当前播放的music所在的位置
	 * */
	@SuppressWarnings("unused")
	private int findMusicIndex() {
		int index = 0;
		for (int i = 0; i < musicList.size(); i++) {
			if (musicList.get(i).getFileUrl().toString().equals(musicSource)) {
				index = i;
				break;
			}
		}
		return index;
	}

	/**
	 * 设置MusicImpl类中得数据源
	 * */
	public static void setMusicSource(String musicSource) {
		MusicBase.musicSource = musicSource;
	}

	/**
	 * 开始播放
	 * */
	public void start() {
		setCurrentMusicInfor(musicList.get(currentId));

		File dir = Environment.getExternalStorageDirectory();

		File lrcs = new File(dir.toString() + "/file/music/lrc");

		for (File file : lrcs.listFiles()) {
			String fileStr = file.toString();
			String name = fileStr.substring(fileStr.lastIndexOf('/') + 1,
					fileStr.lastIndexOf('.'));
			if (name.equals(getCurrentMusicInfor().getMusicName())) {
				System.out.println(true);
				System.out.println(file);
				getCurrentMusicInfor().setLrc(new Lrc(file));
				System.out.println(currentMusicInfor.getLrc());
			}
		}

		try {
			player.prepare();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		player.start();
		musicList.get(currentId).addPlayCount();
		musicList.get(currentId).setPlaytime(new Date());
	}

	public void scanMusic() {
		File dir = Environment.getExternalStorageDirectory();

		musicList = new LinkedList<MusicInformation>();

		setMusicList(new File(dir.toString() + "/file/music"));
		setMusicList(new File(dir.toString() + "/file/music/download"));
		letterTable = Words.createLetterTable(musicList);
	}

	/**
	 * 停止播放
	 * */
	public void stop() {
		player.stop();
	}

	/**
	 * 重新开始
	 * */
	public void restart() {
		player.reset();
		try {
			player.setDataSource(musicSource);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		start();
	}

	/**
	 * 下一曲
	 * */
	public void next() {
		switch (seqEnum.ordinal()) {
		case 0:
			Random random = new Random();
			currentId = random.nextInt(musicList.size() - 1);
			break;
		case 1:
			if (currentId == musicList.size() - 1) {
				currentId = 0;
			} else {
				currentId = currentId + 1;
			}
			break;
		case 2:
			if (currentId == musicList.size() - 1) {
				currentId = 0;
			} else {
				currentId = currentId + 1;
			}
			break;
		}
		setMusicSource(musicList.get(currentId).getFileUrl().toString());
		restart();
	}

	/**
	 * 上一曲
	 * */
	public void previous() {
		if (currentId == 0) {
			currentId = musicList.size() - 1;
		} else {
			currentId = currentId - 1;
		}
		setMusicSource(musicList.get(currentId).getFileUrl().toString());
		restart();
	}

	/**
	 * 自动播放
	 * */
	public void autoMusic() {
		System.out.println("自动播放");
		switch (seqEnum.ordinal()) {
		case 0:
			Random random = new Random(new Date().getTime());
			currentId = random.nextInt(musicList.size() - 1);
			break;
		case 1:
			if (currentId == musicList.size() - 1) {
				currentId = 0;
			} else {
				currentId = currentId + 1;
			}
			break;
		case 2:
		}
		setMusicSource(musicList.get(currentId).getFileUrl().toString());
		restart();
	}

	/**
	 * 根据给定的下标播放
	 * */
	public void startFromIndex(int index) {
		currentId = index - 1;
		setMusicSource(musicList.get(currentId).getFileUrl().toString());
		restart();
	}

	/**
	 * 获取当前播放的音乐的信息
	 * */
	public MusicInformation getCurrentMusicInfor() {
		return currentMusicInfor;
	}

	/**
	 * @return MediaPlayer,代表当前播放音乐的相关信息
	 * @see MeidiaPlayer
	 * */
	public MediaPlayer getPlayer() {
		return player;
	}

	/**
	 * @return 返回播放列表的LinkedList链表，链表的每一项保存的是一个MusicInformation
	 * */
	public LinkedList<MusicInformation> getMusicList() {
		return musicList;
	}

	/**
	 * 获取播放模式
	 * 
	 * @return 当前的播放模式的整形值
	 * */
	public int getMode() {
		return mode.getModeOrder().ordinal();
	}

	/**
	 * 设置播放模式：播放模式有一个播放模式类，和一个直接的播放模式变量<br>
	 * 
	 * @param seq
	 *            seq==0随机播放、seq==1顺序播放、seq==2单曲循环
	 * */
	public void setMode(int seq) {
		if (seq == 1) {
			mode.setModeOrder(SeqEnum.random);
		} else if (seq == 2) {
			mode.setModeOrder(SeqEnum.order);
		} else {
			mode.setModeOrder(SeqEnum.loop);
		}
		seqEnum = mode.getModeOrder();
	}

	public void removeMusicById(Integer id) {

		for (MusicInformation inf : musicList) {
			if (inf.getLocalId() == id) {
				musicList.remove(inf);
				break;
			}
		}

		int localId = 0;
		for (MusicInformation inf : musicList) {
			localId++;
			inf.setLocalId(localId);
		}

		letterTable = Words.createLetterTable(musicList);

		orderMap.deleteFromEveryOrder(id);

	}

	public Map<String, LinkedList<Integer>> getLetterTable() {
		return letterTable;
	}

	public LinkedList<Integer> getOrderList() {
		orderList = orderMap.getMusicOrderList();
		return orderList;
	}

	public void addOrderListElement(Integer id) {
		orderList.add(id);
	}

	public void removeOrderListElement(Integer index) {
		orderList.remove(orderList.get(index));
	}

	public MusicOrderMap getOrderMap() {
		return orderMap;
	}
}
