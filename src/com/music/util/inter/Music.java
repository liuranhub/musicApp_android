package com.music.util.inter;

import java.util.LinkedList;

import com.music.util.impl.MusicInformation;
import com.music.util.impl.MusicOrderMap;

import android.media.MediaPlayer;

public interface Music {
	// public void restart() ;
	public int getMode();

	public void autoMusic();

	public void startFromIndex(int index);

	public LinkedList<MusicInformation> getMusicList();

	public MusicInformation getCurrentMusicInfor();

	public MediaPlayer getPlayer();

	public void previous();

	public void next();

	public void stop();

	public void start();

	public void setMode(int seq);

	public LinkedList<Integer> getOrderList();

	public void addOrderListElement(Integer id);

	public void removeOrderListElement(Integer index);

	public MusicOrderMap getOrderMap();

	public void removeMusicById(Integer id);
	
	public void scanMusic();
	
}
