package com.music.util.impl;

public class StudyMusic {
	private int start = 0;
	private int end = 0;
	private boolean singing = false;

	private static StudyMusic study = null;

	private StudyMusic() {

	}

	public static StudyMusic createStudyMusic() {
		if (study == null) {
			study = new StudyMusic();
		}
		return study;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public boolean isSinging() {
		return singing;
	}

	public void setSinging(boolean singing) {
		this.singing = singing;
	}

}
