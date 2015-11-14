package com.music.util.impl;

import android.os.Environment;

import com.music.util.inter.Music;

public class InitUtil {

	static private Integer firstInitEndIndex = null;

	static private Integer oderPopuwinWidth = 550;
	static private Integer oderPopuwinHeight = 800;

	private static final int LETTERHEIGHT = 55;
	private static final int TEXTHEIGHT = 100;
	private static final int TITLEHEIGHT = 0;
	private static final String SERVERURL = "http://192.168.253.1:8080/MusicServer/";

	private static final String PROFILE_MUSIC = Environment
			.getExternalStorageDirectory() + "/file/music.txt";

	private static final String PROFILE_ORDER = Environment
			.getExternalStorageDirectory() + "/file/music_order.txt";

	public static String getServerurl() {
		return SERVERURL;
	}

	public static Integer getFirstInitEndIndex() {
		return firstInitEndIndex;
	}

	public static void setFirstInitEndIndex(Integer firstInitEndIndex) {
		InitUtil.firstInitEndIndex = firstInitEndIndex;
	}

	public static String getProfile_Music() {
		return PROFILE_MUSIC;
	}

	public static String getProfile_Order() {
		return PROFILE_ORDER;
	}

	public static int getLetterheight() {
		return LETTERHEIGHT;
	}

	public static int getTextheight() {
		return TEXTHEIGHT;
	}

	public static int getTitleheight() {
		return TITLEHEIGHT;
	}

	public static Integer getOderPopuwinWidth() {
		return oderPopuwinWidth;
	}

	public static void setOderPopuwinWidth(Integer oderPopuwinWidth) {
		InitUtil.oderPopuwinWidth = oderPopuwinWidth;
	}

	public static Integer getOderPopuwinHeight() {
		return oderPopuwinHeight;
	}

	public static void setOderPopuwinHeight(Integer oderPopuwinHeight) {
		InitUtil.oderPopuwinHeight = oderPopuwinHeight;
	}

	public static Integer initFirstInitEndIndex() {
		Music music = MusicBase.createMusic();
		MusicInformation aMusic = music.getMusicList().get(12);
		char ch = aMusic.getFirstTitleAlphs().charAt(0);
		firstInitEndIndex = ch - 65 + 1;
		return firstInitEndIndex;
	}
}
