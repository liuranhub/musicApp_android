package com.music.util.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import com.music.util.inter.Music;

public class MusicOrderMap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2013738601761475586L;

	private Map<String, LinkedList<Integer>> musicOrderMap = new HashMap<String, LinkedList<Integer>>();

	private Map<String, String> musicOrderImage = new HashMap<String, String>();

	public MusicOrderMap() {

	}

	public Map<String, LinkedList<Integer>> getMusicOrderMap() {
		return musicOrderMap;
	}

	public Map<String, String> getMusicOrderImage() {
		return musicOrderImage;
	}

	public void setMusicOrderImage(Map<String, String> musicOrderImage) {
		this.musicOrderImage = musicOrderImage;
	}

	public boolean addOrder(String orderName) {

		if (orderName == null) {
			return false;
		}
		for (String key : musicOrderMap.keySet()) {
			orderName = orderName.trim();
			key = key.trim();
			if (orderName.equals(key)) {
				return false;
			}
		}

		musicOrderMap.put(orderName, new LinkedList<Integer>());

		return true;
	}

	public void resetOrderName(String oldName, String newName) {
		if (oldName == null || newName == null) {
			return;
		}
		for (String key : musicOrderMap.keySet()) {
			oldName = oldName.trim();
			key = key.trim();
			if (oldName.equals(key)) {
				musicOrderMap.put(newName, musicOrderMap.get(oldName));
				musicOrderMap.remove(oldName);
				break;
			}
		}

	}

	public LinkedList<Integer> getOrder(String orderName) {
		if (orderName == null) {
			return null;
		} else {
			return musicOrderMap.get(orderName);
		}
	}

	public Set<String> getAllOrder() {

		return musicOrderMap.keySet();

	}

	public void addElementTolist(String orderName, Integer id) {
		if (id < 0 || orderName == null) {
			return;
		}

		musicOrderMap.get(orderName).add(id);
	}

	public int getElementNuber(String orderName) {
		return musicOrderMap.get(orderName).size();
	}

	public void deleteElementFromList(String orderName, Integer id) {
		musicOrderMap.get(orderName).remove(id);
	}

	public void deleteElementFromMap(String orderName) {
		Iterator<String> it = musicOrderMap.keySet().iterator();
		while (it.hasNext()) {
			String name = it.next();
			if (orderName.equals(name)) {
				it.remove();
			}
		}
	}

	public void deleteFromEveryOrder(Integer id) {
		for (String key : musicOrderMap.keySet()) {
			LinkedList<Integer> order = musicOrderMap.get(key);
			order.remove(id);
		}
	}

	private LinkedList<Integer> getMusicList() {

		LinkedList<Integer> musicList = new LinkedList<Integer>();
		Music music = MusicBase.createMusic();
		musicList = new LinkedList<Integer>();
		int id = 0;
		for (@SuppressWarnings("unused")
		MusicInformation inf : music.getMusicList()) {
			id++;
			musicList.add(id);
		}

		return musicList;
	}

	/**
	 * @return LinkedList 队列信息
	 * */
	public LinkedList<Integer> getMusicOrderList() {
		return getMusicList();
	}

	private LinkedList<Integer> getAddList() {

		LinkedList<Integer> addList = musicOrderMap.get("addList");
		if (addList == null) {
			addList = new LinkedList<Integer>();
			musicOrderMap.put("addList", addList);
		}
		return addList;
	}

	public void addMusciToAddList(Integer id) {
		getAddList().add(id);
	}

	public void playOrderList(String orderName) {
		LinkedList<Integer> playOrderList = musicOrderMap.get(orderName);
		if (playOrderList == null) {
			playOrderList = new LinkedList<Integer>();
		}
	}

}
