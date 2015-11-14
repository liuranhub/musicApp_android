package com.music.util.impl;

import com.music.bean.User;

public class MusicInstrumentStatus {
	private static boolean playing = false;
	private static boolean downloading = false;
	private static User user = null;
	private static MusicInstrumentStatus musicInstrumentStatus = null;
	private static boolean connectedNetwork = false;

	private MusicInstrumentStatus() {
		user = new User();
	}

	public static MusicInstrumentStatus getInstrumentStatus() {
		if (musicInstrumentStatus == null) {
			musicInstrumentStatus = new MusicInstrumentStatus();
		}

		return musicInstrumentStatus;
	}

	public static boolean isConnectedNetwork() {
		return connectedNetwork;
	}

	public static void setConnectedNetwork(boolean connectedNetwork) {
		MusicInstrumentStatus.connectedNetwork = connectedNetwork;
	}

	public boolean isPlaying() {
		return playing;
	}

	public void setPlaying(boolean playing) {
		MusicInstrumentStatus.playing = playing;
	}

	public boolean isDownloading() {
		return downloading;
	}

	public void setDownloading(boolean downloading) {
		MusicInstrumentStatus.downloading = downloading;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		MusicInstrumentStatus.user = user;
	}

}
