package com.music.util.impl;

import com.music.enums.SeqEnum;

public class PlayMode {
	private static SeqEnum modeOrder = SeqEnum.random;
	private static PlayMode playMode ;
	
	private PlayMode() {

	}

	public static PlayMode createPlayMode(){
		if(playMode==null){
			return new PlayMode() ;
		}
		else{
			return playMode ;
		}
	}
	
	public SeqEnum getModeOrder() {
		return modeOrder;
	}

	public void setModeOrder(SeqEnum modeOrder) {
		PlayMode.modeOrder = modeOrder;
	}

}
