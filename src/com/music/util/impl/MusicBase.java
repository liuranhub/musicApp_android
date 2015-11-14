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
 * ����ģʽ��ʵ��
 * */
public class MusicBase implements Music {

	/**
	 * �������ֲ��ŵĲ���ģʽ����Ϊ�Ͳ���ģʽ��ص����ݶ�����MusicImp���н��е�����������浥���ļ���һ������<br>
	 * Ϊ�˷�ֹ�������Ҫ�����ﻹ����������һ��PlayMode��
	 * */
	private static SeqEnum seqEnum = SeqEnum.random;
	private static PlayMode mode = PlayMode.createPlayMode();

	/**
	 * ���������������Ϣ�ܱ����ÿһ���һ��MusicInformation��<br>
	 * ��������û�׸�ľ�����Ϣ,��initData������Ҫ���г�ʼ��
	 * */
	private static LinkedList<MusicInformation> musicList = new LinkedList<MusicInformation>();

	/**
	 * player һ��MediaPlayer�ľ���ʵ�������Ų���һ�׸裬<br>
	 * ͬʱ�ṩ��������ʱ��һЩ��̬��Ϣ,��������dataSource���Կ���������ֵ��л�
	 * */
	private static MediaPlayer player = new MediaPlayer();

	/**
	 * ΪMediaPlayer�ṩ����Դ
	 * */
	private static String musicSource = "";

	/**
	 * ����ģʽ��Ϊ�ⲿ����ȡ������
	 * */
	private static MusicBase music;

	/**
	 * currentId ��ǰ���ڲ��ŵ����õ�id����musicList�е��±�λ�ã�<br>
	 * ���Ը�������ȡ��Ӧ��MusicInformation����,0<=currentId<musicList.size()
	 * */
	private static Integer currentId = 2;

	/**
	 * currentMusicInfor Ϊ�˷��������������ǵ�ǰ���ڲ������ֵ���Ϣ
	 * */
	private static MusicInformation currentMusicInfor = new MusicInformation();

	/**
	 * ��ĸ��
	 * */
	private static Map<String, LinkedList<Integer>> letterTable = new HashMap<String, LinkedList<Integer>>();

	private static LinkedList<Integer> orderList = new LinkedList<Integer>();

	private static MusicOrderMap orderMap = (MusicOrderMap) new SerializUtil()
			.readMusicBean(InitUtil.getProfile_Order());;

	private MusicBase() {
		initData();
	}

	/**
	 * ����Music
	 * 
	 * @return Music�ӿڵ�һ��ʵ��
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
	 * ��ʼ��������<br>
	 * 1��musicList��ʼ��<br>
	 * 2��musicSource��ʼ��<br>
	 * 3��currentId��ʼ��<br>
	 * 4��player��ʼ���ݵ�����
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
			 * �������,��nextһ������ֳ�ʼ��ŵĴ���
			 * */
			if (currentId >= musicList.size()) {
				currentId = musicList.size() - 1;
			} else if (currentId < 1) {
				currentId = 1;
			}
			/**
			 * ����������������ģ����ǲ�û�и���
			 * */
			seqEnum = musicBean.getSeqEnum();
			mode.setModeOrder(seqEnum);
		}

		if (orderMap == null) {
			orderMap = new MusicOrderMap();
		}

		/**
		 * ����������������ط�����һ���жϣ������musicList==null��ʱ��Ž�������Ĳ�����
		 * ��������Ѿ��ö�ȡ�����ļ��ķ�ʽ����������б�
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
	 * ��һ�μ���ʱ����musicList��
	 * */
	private void setMusicList(File file) {
		File[] files = file.listFiles();

		/**
		 * ����musicList
		 * */
		for (File f : files) {
			if (!f.isDirectory()) {
				musicList.add(setOneMusicInfromation(f));
			}
		}

		/**
		 * ��musicList��������
		 * */
		sortMusicListForName();

		/**
		 * ��������������ļ���Id
		 * */
		int id = 0;
		for (MusicInformation music : musicList) {
			id++;
			music.setLocalId(id);
		}

	}

	/**
	 * �Բ����б��������
	 * */
	private void sortMusicListForName() {
		new SortMusic().sortByComparable();
	}

	/**
	 * ����musicList��ÿһ��MusicInformation������
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
		 * �������Ƿ����������ļ�
		 * */
		if (f.toString().contains("download")) {
			musicInf.setDownload(true);
		}

		return musicInf;
	}

	/**
	 * ���ҵ�ǰ���ŵ�music���ڵ�λ��
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
	 * ����MusicImpl���е�����Դ
	 * */
	public static void setMusicSource(String musicSource) {
		MusicBase.musicSource = musicSource;
	}

	/**
	 * ��ʼ����
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
	 * ֹͣ����
	 * */
	public void stop() {
		player.stop();
	}

	/**
	 * ���¿�ʼ
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
	 * ��һ��
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
	 * ��һ��
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
	 * �Զ�����
	 * */
	public void autoMusic() {
		System.out.println("�Զ�����");
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
	 * ���ݸ������±겥��
	 * */
	public void startFromIndex(int index) {
		currentId = index - 1;
		setMusicSource(musicList.get(currentId).getFileUrl().toString());
		restart();
	}

	/**
	 * ��ȡ��ǰ���ŵ����ֵ���Ϣ
	 * */
	public MusicInformation getCurrentMusicInfor() {
		return currentMusicInfor;
	}

	/**
	 * @return MediaPlayer,����ǰ�������ֵ������Ϣ
	 * @see MeidiaPlayer
	 * */
	public MediaPlayer getPlayer() {
		return player;
	}

	/**
	 * @return ���ز����б��LinkedList���������ÿһ������һ��MusicInformation
	 * */
	public LinkedList<MusicInformation> getMusicList() {
		return musicList;
	}

	/**
	 * ��ȡ����ģʽ
	 * 
	 * @return ��ǰ�Ĳ���ģʽ������ֵ
	 * */
	public int getMode() {
		return mode.getModeOrder().ordinal();
	}

	/**
	 * ���ò���ģʽ������ģʽ��һ������ģʽ�࣬��һ��ֱ�ӵĲ���ģʽ����<br>
	 * 
	 * @param seq
	 *            seq==0������š�seq==1˳�򲥷š�seq==2����ѭ��
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
