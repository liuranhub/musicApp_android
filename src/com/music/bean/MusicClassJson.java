package com.music.bean;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MusicClassJson {

	private Map<String, List<MusicTypeJson>> map = new TreeMap<String, List<MusicTypeJson>>();

	public void put(String className, List<MusicTypeJson> types) {
		map.put(className, types);
	}

	public Map<String, List<MusicTypeJson>> getMap() {
		return map;
	}
}
