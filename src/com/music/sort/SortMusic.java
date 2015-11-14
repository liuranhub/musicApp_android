package com.music.sort;

import java.util.Collections;
import java.util.Date;

import com.music.util.impl.MusicBase;
import com.music.util.impl.MusicInformation;
import com.music.util.inter.Music;

public class SortMusic {

	Music music = MusicBase.createMusic();

	public SortMusic() {

	}

	public void sortByComparable(){
		Collections.sort(music.getMusicList());
	}
	
	public Object[] sortByPlayTime() {
		Object[] musics = music.getMusicList().toArray();
		Integer max = 0;
		for (int i = 0; i < 40; i++) {
			max = i;
			for (int j = i; j < musics.length; j++) {
				Date mj = ((MusicInformation) musics[j]).getPlaytime();

				Date mm = ((MusicInformation) musics[max]).getPlaytime();

				long tmj = 0;
				if (mj != null) {
					tmj = mj.getTime();
				}

				long tmm = 0;
				if (mm != null) {
					tmm = mm.getTime();
				}
				if (tmm < tmj) {
					max = j;
				}
			}
			Object temp = 0;
			temp = musics[i];
			musics[i] = musics[max];
			musics[max] = temp;
		}
		return musics;
	}

	public Object[] sortByPlayCount() {
		Object[] musics = music.getMusicList().toArray();
		Integer max = 0;
		for (int i = 0; i < 40; i++) {
			max = i;
			for (int j = i; j < musics.length; j++) {
				MusicInformation mj = (MusicInformation) musics[j];

				MusicInformation mm = (MusicInformation) musics[max];
				if (mm.getPalyCount() < mj.getPalyCount()) {
					max = j;
				}
			}
			Object temp = 0;
			temp = musics[i];
			musics[i] = musics[max];
			musics[max] = temp;
		}
		return musics;
	}

}
