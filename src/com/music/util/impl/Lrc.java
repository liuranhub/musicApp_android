package com.music.util.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Lrc implements Serializable{

	private static final long serialVersionUID = 1L;

	private File file = null;

	private String ti = null;
	private String ar = null;
	private String by = null;
	private int offset = 0;

	private Map<Integer, String> lrcMap = new TreeMap<Integer, String>();

	public Lrc(File file) {
		this.file = file;
		initInf();
	}

	public Lrc(String fileStr) {
		file = new File(fileStr);
		initInf();
	}

	private void initInf() {

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "utf-8"));
			String line = null;
			while ((line = reader.readLine()) != null) {

				int start = line.indexOf('[') + 1;
				int end = line.indexOf(']');

				if (start > 0 && end > 0) {

					if (line.contains("ti")) {
						ti = line.substring(start + 3, end);
					}
					if (line.contains("ar")) {
						ar = line.substring(start + 3, end);
					}
					if (line.contains("offset")) {
						offset = Integer.parseInt(line
								.substring(start + 7, end));
					}
					if (line.contains("by")) {
						by = line.substring(start + 3, end);
					}

					if (line.length() > end + 1) {
						String timeStr = line.substring(start, end);

						String minute = timeStr.substring(0,
								timeStr.indexOf(':'));

						String second = timeStr.substring(
								timeStr.indexOf(':') + 1, timeStr.indexOf('.'));

						String lrc = line.substring(end + 1).trim();

						int time = Integer.parseInt(minute) * 60
								+ Integer.parseInt(second);

						lrcMap.put(time, lrc);
					}
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getTi() {
		return ti;
	}

	public String getAr() {
		return ar;
	}

	public String getBy() {
		return by;
	}

	public int getOffset() {
		return offset;
	}

	public Map<Integer, String> getLrcMap() {
		return lrcMap;
	}

	public List<String> getLrcList() {
		return null ;
	}

	@Override
	public String toString() {

		StringBuffer str = new StringBuffer("file=" + file + "\nti=" + ti
				+ "\nar=" + ar + "\nby=" + by + "\noffset=" + offset);

		for (Integer key : lrcMap.keySet()) {
			str.append("\n").append(key).append("\t").append(lrcMap.get(key));
		}

		return str.toString();
	}

}
