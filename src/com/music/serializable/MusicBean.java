package com.music.serializable;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

import android.os.Environment;

import com.music.enums.SeqEnum;
import com.music.util.impl.MusicInformation;

public class MusicBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private SeqEnum seqEnum = SeqEnum.order;

	private LinkedList<MusicInformation> musicList = null;

	private Integer currentId = 0;

	private Map<String, LinkedList<Integer>> letterTable = new TreeMap<String, LinkedList<Integer>>();

	private String downloadStr = Environment.getExternalStorageDirectory()
			+ "/file/download";

	private String musicStr = Environment.getExternalStorageDirectory()
			+ "/file/music";

	public Map<String, LinkedList<Integer>> getLetterTable() {
		return letterTable;
	}

	public void setLetterTable(Map<String, LinkedList<Integer>> letterTable) {
		this.letterTable = letterTable;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDownloadStr() {
		return downloadStr;
	}

	public void setDownloadStr(String downloadStr) {
		this.downloadStr = downloadStr;
	}

	public String getMusicStr() {
		return musicStr;
	}

	public void setMusicStr(String musicStr) {
		this.musicStr = musicStr;
	}

	public MusicBean() {

	}

	public SeqEnum getSeqEnum() {
		return seqEnum;
	}

	public void setSeqEnum(SeqEnum seqEnum) {
		this.seqEnum = seqEnum;
	}

	public LinkedList<MusicInformation> getMusicList() {
		return musicList;
	}

	public void setMusicList(LinkedList<MusicInformation> musicList) {
		this.musicList = musicList;
	}

	public Integer getCurrentId() {
		return currentId;
	}

	public void setCurrentId(Integer currentId) {
		this.currentId = currentId;
	}

	@Override
	public String toString() {
		return "MusicBean [seqEnum=" + seqEnum + ", musicList=" + musicList
				+ ", currentId=" + currentId + ", letterTable=" + letterTable
				+ ", downloadStr=" + downloadStr + ", musicStr=" + musicStr
				+ "]";
	}

}
