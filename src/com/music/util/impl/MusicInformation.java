package com.music.util.impl;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

public class MusicInformation implements Comparable<MusicInformation>,
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 音乐名
	 * */
	private String musicName = "null";

	/**
	 * 歌手
	 * */
	private String musicSinger = "unknown";
	/**
	 * 文件路径
	 * */
	private File fileUrl = null;
	/**
	 * 服务器上id
	 * */
	private Integer serverId = null;
	/**
	 * 本地播放列表id
	 * */
	private Integer localId = null;
	/**
	 * music时长
	 * */
	private Integer musicTime = null;
	/**
	 * 音乐类型
	 * */
	private String special = "nuknown";
	/**
	 * 是否是偏好歌曲
	 * */
	private boolean like = false;

	private boolean download = false;

	private boolean downloaded = false;

	private int palyCount = 0;

	private Date playtime = null;

	/**
	 * 用户自定义列表
	 * */
	private String userType = null;

	private LinkedList<String> titleAlphs = new LinkedList<String>();

	private String sortString = "";

	private Lrc lrc = null;

	public void addPlayCount() {
		palyCount = palyCount + 1;
		System.out.println(palyCount);
	}

	public boolean haslrc() {
		if (lrc != null) {
			return true;
		} else {
			return false;
		}
	}

	public void setLrc(Lrc lrc) {
		this.lrc = lrc;
	}

	public Lrc getLrc() {
		return lrc;
	}

	public boolean isDownload() {
		return download;
	}

	public void setDownload(boolean download) {
		this.download = download;
	}

	public boolean isDownloaded() {
		return downloaded;
	}

	public void setDownloaded(boolean downloaded) {
		this.downloaded = downloaded;
	}

	public int getPalyCount() {
		return palyCount;
	}

	public void setPalyCount(int palyCount) {
		this.palyCount = palyCount;
	}

	public Date getPlaytime() {
		return playtime;
	}

	public void setPlaytime(Date playtime) {
		this.playtime = playtime;
	}

	public MusicInformation() {

	}

	public String getMusicName() {
		return musicName;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
		/**
		 * 获取音乐名的首字母列表
		 * */
		titleAlphs = Words.getWordTitles(musicName, musicName.length());
		StringBuffer title = new StringBuffer();
		for (String w : titleAlphs) {
			title.append(w);
		}

		sortString = title.toString().trim();
		System.out.println(musicName);
		if (musicName.contains("-")) {
			setMusicSinger(musicName.substring(0, musicName.indexOf("-")));
			System.out.println(musicSinger);
		}

	}

	public String getMusicSinger() {
		return musicSinger;
	}

	public void setMusicSinger(String musicSinger) {
		this.musicSinger = musicSinger;
	}

	public File getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(File fileUrl) {
		this.fileUrl = fileUrl;
	}

	public Integer getServerId() {
		return serverId;
	}

	public void setServerId(Integer serverId) {
		this.serverId = serverId;
	}

	public Integer getLocalId() {
		return localId;
	}

	public void setLocalId(Integer localId) {
		this.localId = localId;
	}

	public Integer getMusicTime() {
		return musicTime;
	}

	public void setMusicTime(Integer musicTime) {
		this.musicTime = musicTime;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public boolean isLike() {
		return like;
	}

	public void setLike(boolean like) {
		this.like = like;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public LinkedList<String> getTitleAlphs() {
		return titleAlphs;
	}

	public void addTitleAlphs(String title) {
		titleAlphs.add(title);
	}

	public String getSortString() {
		return sortString;
	}

	public String getFirstTitleAlphs() {
		return titleAlphs.getFirst().trim();
	}

	public String toString() {
		return "musicName=" + musicName + sortString + ", fileUrl=" + fileUrl
				+ ", localId=" + localId;
	}

	/**
	 * 排序
	 * */
	@Override
	public int compareTo(MusicInformation another) {
		return this.sortString.compareTo(another.getSortString());
	}
}
